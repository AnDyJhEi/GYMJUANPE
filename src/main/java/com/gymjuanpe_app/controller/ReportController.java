package com.gymjuanpe_app.controller;

import com.gymjuanpe_app.model.Payment;
import com.gymjuanpe_app.repository.PaymentRepository;
import com.gymjuanpe_app.repository.ReservationRepository;
import com.gymjuanpe_app.repository.ClassRepository;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

  private final PaymentRepository paymentRepo;
  private final ReservationRepository reservationRepo;
  private final ClassRepository classRepo;

  public ReportController(PaymentRepository paymentRepo, ReservationRepository reservationRepo, ClassRepository classRepo){
    this.paymentRepo=paymentRepo; this.reservationRepo=reservationRepo; this.classRepo=classRepo;
  }

  @GetMapping("/dashboard")
  public Map<String,Object> dashboard(){
    List<Payment> pays = paymentRepo.findAll();
    double ingresos = pays.stream().filter(p->p.getStatus().name().equals("PAID")).mapToDouble(Payment::getAmount).sum();
    long totalPagos = pays.size();
    long totalReservas = reservationRepo.count();

    // clases ocupadas = reservedCount/capacity
    var clases = classRepo.findAll();
    long clasesOcupadas = clases.stream().filter(c -> c.getReservedCount() > 0).count();

    return Map.of(
      "ingresosTotal", ingresos,
      "totalPagos", totalPagos,
      "totalReservas", totalReservas,
      "clasesConReservas", clasesOcupadas
    );
  }

  @GetMapping(value="/payments.csv", produces="text/csv")
  public ResponseEntity<byte[]> paymentsCsv(){
    StringBuilder sb = new StringBuilder();
    sb.append("id,userId,planId,amount,status,createdAt,receiptCode\n");
    for(Payment p: paymentRepo.findAll()){
      sb.append(p.getId()).append(",")
        .append(p.getUserId()).append(",")
        .append(p.getPlanId()).append(",")
        .append(p.getAmount()).append(",")
        .append(p.getStatus()).append(",")
        .append(p.getCreatedAt()).append(",")
        .append(p.getReceiptCode()).append("\n");
    }
    byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=payments.csv")
      .contentType(MediaType.parseMediaType("text/csv"))
      .body(bytes);
  }

  @GetMapping(value="/payments.pdf", produces=MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<byte[]> paymentsPdf() throws Exception {
    PDDocument doc = new PDDocument();
    PDPage page = new PDPage();
    doc.addPage(page);

    PDPageContentStream cs = new PDPageContentStream(doc, page);
    cs.beginText();
    cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
    cs.newLineAtOffset(50, 750);
    cs.showText("Gym Juanpe - Reporte de Pagos");
    cs.endText();

    cs.beginText();
    cs.setFont(PDType1Font.HELVETICA, 10);
    cs.newLineAtOffset(50, 720);

    int lines = 0;
    for(Payment p: paymentRepo.findAll()){
      String line = String.format("Pago #%d | user:%d plan:%d | S/ %.2f | %s | %s",
        p.getId(), p.getUserId(), p.getPlanId(), p.getAmount(), p.getStatus(), p.getCreatedAt());
      cs.showText(line);
      cs.newLineAtOffset(0, -14);
      lines++;
      if(lines >= 30) break; // demo
    }
    cs.endText();
    cs.close();

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    doc.save(baos);
    doc.close();

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=payments.pdf")
      .contentType(MediaType.APPLICATION_PDF)
      .body(baos.toByteArray());
  }
}

package pl.javakurs.dname074.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invoice {
    private Long id;
    private Long externalProviderId;
    private Long orderId;
    private String invoiceNumber;
    private String status;
    private Instant createdAt;
    private String pdfDownloadUrl;

    public void fulfillData(Long orderId) {
        this.createdAt = Instant.now();
        this.pdfDownloadUrl = String.format("https://electronics-store-test-project.fakturownia.pl/invoices/%d.pdf?api_token=bwzuekW26k6ZpfrSJ1kb",
                        this.externalProviderId);
        this.orderId = orderId;
    }
}

package sample;

public class DeliverySummaryManager {
    public enum DeliveryType {
        DELIVERY,
        PICKUP
    }
    public enum PaymentType {
        ONLINE,
        IN_PERSON
    }

    public static DeliveryType deliveryType;
    public static PaymentType paymentType;
    public static String date;

    public static boolean allSelectionsMade() {
        return deliveryType != null && paymentType != null;
    }

    public static String getDeliverySummary() {
        String deliveryString = "Välj ett leveranssätt";
        if (deliveryType != null) {
            if (deliveryType == DeliveryType.DELIVERY) {
                deliveryString = "Dina varor kommer att levereras till din adress " + date + ".";
            } else {
                deliveryString = "Du kommer hämta dina varor på ELEKTROGÅRDEN 1 (412 58 Göteborg) " + date + ".";
            }
        }
        String paymentString = "Välj ett betalningssätt";
        if (paymentType != null) {
            if (paymentType == PaymentType.ONLINE) {
                paymentString = "Betalning för varorna kommer att ske över internet med kort.";
            } else {
                paymentString = "Betalning för varorna kommer att ske kontant när du får dem.";
            }
        }
        return deliveryString + "\n\n" + paymentString;
    }
}

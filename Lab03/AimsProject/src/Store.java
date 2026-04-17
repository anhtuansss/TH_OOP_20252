public class Store {
    public static final int MAX_NUMBERS_ORDERED = 20;  
    private DigitalVideoDisc itemsInStore[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyInStore = 0;

    public void addDVD(DigitalVideoDisc disc) {
        if (qtyInStore < MAX_NUMBERS_ORDERED) {
            itemsInStore[qtyInStore] = disc;
            qtyInStore++;
            System.out.println("The disc has been added to the store");
        } else {
            System.out.println("The store is full. Cannot add more discs.");
        }
    }

    public void removeDVD(DigitalVideoDisc disc) {
        if (qtyInStore == 0) {
            System.out.println("The store is empty. Nothing to remove.");
            return;
        }
        
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i] == disc) { 
                for (int j = i; j < qtyInStore - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }
                itemsInStore[qtyInStore - 1] = null; 
                qtyInStore--;
                System.out.println("The disc has been removed from the store");
                return;
            }
        }
        System.out.println("The disc is not in the store");
    }
}

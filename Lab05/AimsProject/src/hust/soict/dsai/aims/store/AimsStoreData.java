package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.store.Store;

public class AimsStoreData {
    private static Store store;

    public static Store getStore() {
        return store;
    }

    public static void setStore(Store store) {
        AimsStoreData.store = store;
    }
}

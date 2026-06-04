package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

import javax.swing.JTextField;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private final JTextField tfAuthors = new JTextField(20);

    public AddBookToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add Book");
        addRow("Authors", tfAuthors);
        showScreen();
    }

    @Override
    protected Media createMedia() {
        Book book = new Book(
                readInt(tfId, "Id"),
                readRequired(tfTitle, "Title"),
                readRequired(tfCategory, "Category"),
                readFloat(tfCost, "Cost")
        );

        String authors = readOptional(tfAuthors);
        if (!authors.isEmpty()) {
            for (String author : authors.split(",")) {
                String trimmedAuthor = author.trim();
                if (!trimmedAuthor.isEmpty()) {
                    book.addAuthor(trimmedAuthor);
                }
            }
        }

        return book;
    }
}

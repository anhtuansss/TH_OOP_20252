package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;

import javax.swing.JTextField;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private final JTextField tfDirector = new JTextField(20);
    private final JTextField tfArtist = new JTextField(20);
    private final JTextField tfTrackTitle = new JTextField(20);
    private final JTextField tfTrackLength = new JTextField(20);

    public AddCompactDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add CD");
        addRow("Director", tfDirector);
        addRow("Artist", tfArtist);
        addRow("Track title", tfTrackTitle);
        addRow("Track length", tfTrackLength);
        showScreen();
    }

    @Override
    protected Media createMedia() {
        CompactDisc cd = new CompactDisc(
                readInt(tfId, "Id"),
                readRequired(tfTitle, "Title"),
                readRequired(tfCategory, "Category"),
                readFloat(tfCost, "Cost"),
                readRequired(tfDirector, "Director"),
                readRequired(tfArtist, "Artist")
        );

        String trackTitle = readOptional(tfTrackTitle);
        if (!trackTitle.isEmpty()) {
            cd.addTrack(new Track(trackTitle, readInt(tfTrackLength, "Track length")));
        }

        return cd;
    }
}

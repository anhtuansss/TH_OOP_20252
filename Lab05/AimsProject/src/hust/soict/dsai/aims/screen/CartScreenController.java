package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.AimsStoreData;
import hust.soict.dsai.aims.store.Store;

import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;

public class CartScreenController {
    private Cart cart;
    private JFrame currentFrame;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediacategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private Label totalCostLabel;

    public CartScreenController(Cart cart, JFrame currentFrame) {
        this.cart = cart;
        this.currentFrame = currentFrame;
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediacategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        tblMedia.setItems(cart.getItemsOrdered());

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateButtonBar(newValue);
        });

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            showFilteredMedia();
        });

        cart.getItemsOrdered().addListener((ListChangeListener<Media>) change -> {
            updateTotalCost();
            showFilteredMedia();
        });

        radioBtnFilterId.setOnAction(e -> showFilteredMedia());
        radioBtnFilterTitle.setOnAction(e -> showFilteredMedia());

        updateTotalCost();
    }

    private void updateButtonBar(Media media) {
        btnRemove.setVisible(media != null);

        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
        }
    }

    @FXML
    void removeButtonPressed(ActionEvent event) {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();

        if (selected != null) {
            cart.removeMedia(selected);
            updateTotalCost();
            showFilteredMedia();
            updateButtonBar(null);
        }
    }

    @FXML
    void playButtonPressed(ActionEvent event) {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();

        if (selected instanceof Playable) {
            try {
                ((Playable) selected).play();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Play media");
                alert.setHeaderText(null);
                alert.setContentText("Playing: " + selected.getTitle());
                alert.showAndWait();
            } catch (PlayerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Play error");
                alert.setHeaderText("Cannot play media");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    void placeOrderPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order");
        alert.setHeaderText(null);
        alert.setContentText("Order placed successfully!");

        cart.getItemsOrdered().clear();
        updateTotalCost();
        showFilteredMedia();

        alert.showAndWait();
    }

    @FXML
    void viewStorePressed(ActionEvent event) {
        Store store = AimsStoreData.getStore();
        if (store == null) {
            store = new Store();
            AimsStoreData.setStore(store);
        }

        new StoreScreen(store, cart);
        currentFrame.dispose();
    }

    private void updateTotalCost() {
        totalCostLabel.setText(String.format("%.2f $", cart.totalCost()));
    }

    private void showFilteredMedia() {
        String filter = tfFilter.getText();

        if (filter == null || filter.trim().isEmpty()) {
            tblMedia.setItems(cart.getItemsOrdered());
            return;
        }

        FilteredList<Media> filteredList = new FilteredList<>(cart.getItemsOrdered());

        filteredList.setPredicate(media -> {
            if (radioBtnFilterId.isSelected()) {
                return String.valueOf(media.getId()).contains(filter);
            } else {
                return media.getTitle().toLowerCase().contains(filter.toLowerCase());
            }
        });

        tblMedia.setItems(filteredList);
    }
}

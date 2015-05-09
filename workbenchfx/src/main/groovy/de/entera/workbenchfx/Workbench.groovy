package de.entera.workbenchfx

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.control.Control
import javafx.scene.control.Skin

import de.entera.workbenchfx.impl.WorkbenchSkin

class Workbench extends Control {

    //---------------------------------------------------------------------------------------------
    // CONSTANTS.
    //---------------------------------------------------------------------------------------------

    private static final String STYLE_CLASS_DEFAULT = "workbench"

    //---------------------------------------------------------------------------------------------
    // FIELDS.
    //---------------------------------------------------------------------------------------------

    final Node getContent() { return this.contentProperty().get() }

    final void setContent(Node value) { this.contentProperty().set(value) }

    final ObjectProperty<Node> contentProperty() { return this.content }

    final ObservableList<Node> getLeftViews() { return this.leftViews }

    final ObservableList<Node> getRightViews() { return this.rightViews }

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private final ObjectProperty<Node> content = new SimpleObjectProperty(this, "content")

    private final ObservableList<Node> leftViews = FXCollections.observableArrayList()

    private final ObservableList<Node> rightViews = FXCollections.observableArrayList()

    //---------------------------------------------------------------------------------------------
    // CONSTRUCTORS.
    //---------------------------------------------------------------------------------------------

    public Workbench() {
        this.styleClass << STYLE_CLASS_DEFAULT
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    protected void layoutChildren() {
        super.layoutChildren()
    }

    protected Skin<Workbench> createDefaultSkin() {
        return new WorkbenchSkin(this)
    }

}

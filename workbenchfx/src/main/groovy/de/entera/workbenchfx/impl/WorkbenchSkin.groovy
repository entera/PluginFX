package de.entera.workbenchfx.impl

import javafx.application.Platform
import javafx.collections.ListChangeListener
import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.SplitPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.StackPane

import com.sun.javafx.scene.control.behavior.BehaviorBase
import com.sun.javafx.scene.control.skin.BehaviorSkinBase
import de.entera.workbenchfx.Workbench

class WorkbenchSkin extends BehaviorSkinBase<Workbench, BehaviorBase<Workbench>> {

    //---------------------------------------------------------------------------------------------
    // CONSTANTS.
    //---------------------------------------------------------------------------------------------

    private static final String PROPERTY_CONTENT = "content"

    private static final String PROPERTY_LEFT_VIEWS = "left-views"

    private static final String STYLE_CLASS_CONTENT_CONTAINER = "content-container"

    private static final String STYLE_CLASS_LEFT_CONTAINER = "left-container"

    private static final String STYLE_CLASS_RIGHT_CONTAINER = "right-container"

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private StackPane rootContainer

    private BorderPane innerBorderPane

    private StackPane contentContainer

    private SplitPane leftContainer

    private SplitPane rightContainer

    //---------------------------------------------------------------------------------------------
    // CONSTRUCTORS.
    //---------------------------------------------------------------------------------------------

    public WorkbenchSkin(Workbench workbench) {
        super(workbench, new BehaviorBase(workbench, []))

        this.initializeSkin()
        this.installSkin(rootContainer)
        this.installBehavior()
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    private void initializeSkin() {
        this.contentContainer = new StackPane()
        this.contentContainer.styleClass << STYLE_CLASS_CONTENT_CONTAINER

        this.leftContainer = new SplitPane()
        this.leftContainer.styleClass << STYLE_CLASS_LEFT_CONTAINER
        this.leftContainer.orientation = Orientation.VERTICAL

        this.rightContainer = new SplitPane()
        this.rightContainer.styleClass << STYLE_CLASS_RIGHT_CONTAINER
        this.rightContainer.orientation = Orientation.VERTICAL

        this.innerBorderPane = new BorderPane()
        this.innerBorderPane.center = this.contentContainer
        this.innerBorderPane.left = this.leftContainer
        this.innerBorderPane.right = this.rightContainer

        this.rootContainer = new StackPane()
        this.rootContainer.children.setAll(this.innerBorderPane)
    }

    private void installSkin(Parent container) {
        this.children.setAll(container.childrenUnmodifiable)
        this.skinnable.stylesheets.setAll(container.stylesheets)
    }

    private void installBehavior() {
        this.skinnable.leftViews.addListener({ change ->
            handleLeftViewsListChanged()
        } as ListChangeListener<Node>)
        this.skinnable.rightViews.addListener({ change ->
            handleRightViewsListChanged()
        } as ListChangeListener<Node>)

        registerChangeListener(this.skinnable.contentProperty(), PROPERTY_CONTENT)
        handleControlPropertyChanged(PROPERTY_CONTENT)
    }

    protected void handleControlPropertyChanged(String propertyReference) {
        super.handleControlPropertyChanged(propertyReference)

        if (propertyReference == PROPERTY_CONTENT) {
            this.handleContentPropertyChanged()
        }
    }

    private void handleContentPropertyChanged() {
        assert Platform.isFxApplicationThread()

        def content = this.skinnable.contentProperty().get()
        if (content) {
            this.contentContainer.children.setAll(content)
        }
    }

    private void handleLeftViewsListChanged() {
        assert Platform.isFxApplicationThread()

        def leftViews = this.skinnable.leftViews
        this.leftContainer.items.setAll(leftViews)
    }

    private void handleRightViewsListChanged() {
        assert Platform.isFxApplicationThread()

        def rightViews = this.skinnable.rightViews
        this.rightContainer.items.setAll(rightViews)
    }

    protected void layoutChildren(double x,
                                  double y,
                                  double width,
                                  double height) {
        super.layoutChildren(x, y, width, height)
    }

}

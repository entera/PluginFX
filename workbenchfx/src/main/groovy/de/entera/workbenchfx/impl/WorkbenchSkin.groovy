package de.entera.workbenchfx.impl

import javafx.application.Platform
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import javafx.geometry.Orientation
import javafx.scene.Parent
import javafx.scene.control.SplitPane
import javafx.scene.control.ToolBar
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

    private static final String PROPERTY_RIGHT_VIEWS = "right-views"

    private static final String PROPERTY_LEFT_BUTTONS = "left-buttons"

    private static final String PROPERTY_RIGHT_BUTTONS = "right-buttons"

    private static final String STYLE_CLASS_CONTAINER = "container"

    private static final String STYLE_CLASS_CONTENT = "content"

    private static final String STYLE_CLASS_LEFT = "left"

    private static final String STYLE_CLASS_RIGHT = "right"

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private StackPane rootContainer

    private BorderPane outerBorderPane

    private BorderPane innerBorderPane

    private StackPane contentContainer

    private SplitPane leftContainer

    private SplitPane rightContainer

    private ToolBar leftToolBar

    private ToolBar rightToolBar

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
        this.contentContainer.styleClass << STYLE_CLASS_CONTENT << STYLE_CLASS_CONTAINER

        this.leftContainer = new SplitPane()
        this.leftContainer.styleClass << STYLE_CLASS_LEFT << STYLE_CLASS_CONTAINER
        this.leftContainer.orientation = Orientation.VERTICAL

        this.rightContainer = new SplitPane()
        this.rightContainer.styleClass << STYLE_CLASS_RIGHT << STYLE_CLASS_CONTAINER
        this.rightContainer.orientation = Orientation.VERTICAL

        this.leftToolBar = new ToolBar()
        this.leftToolBar.styleClass << STYLE_CLASS_LEFT

        this.rightToolBar = new ToolBar()
        this.rightToolBar.styleClass << STYLE_CLASS_RIGHT

        this.innerBorderPane = new BorderPane()
        this.innerBorderPane.center = this.contentContainer
        this.innerBorderPane.left = this.leftContainer
        this.innerBorderPane.right = this.rightContainer

        this.outerBorderPane = new BorderPane()
        this.outerBorderPane.center = this.innerBorderPane
        this.outerBorderPane.left = this.leftToolBar
        this.outerBorderPane.right = this.rightToolBar

        this.rootContainer = new StackPane()
        this.rootContainer.children.setAll(this.outerBorderPane)
    }

    private void installSkin(Parent container) {
        this.children.setAll(container.childrenUnmodifiable)
        this.skinnable.stylesheets.setAll(container.stylesheets)
    }

    private void installBehavior() {
        this.registerChangeListener(this.skinnable.contentProperty(), PROPERTY_CONTENT)
        this.registerListChangeListener(this.skinnable.leftViews, PROPERTY_LEFT_VIEWS)
        this.registerListChangeListener(this.skinnable.rightViews, PROPERTY_RIGHT_VIEWS)
        this.registerListChangeListener(this.skinnable.leftButtons, PROPERTY_LEFT_BUTTONS)
        this.registerListChangeListener(this.skinnable.rightButtons, PROPERTY_RIGHT_BUTTONS)

        this.handleControlPropertyChanged(PROPERTY_CONTENT)
        this.handleControlPropertyChanged(PROPERTY_LEFT_VIEWS)
        this.handleControlPropertyChanged(PROPERTY_RIGHT_VIEWS)
        this.handleControlPropertyChanged(PROPERTY_LEFT_BUTTONS)
        this.handleControlPropertyChanged(PROPERTY_RIGHT_BUTTONS)
    }

    protected void handleControlPropertyChanged(String propertyReference) {
        super.handleControlPropertyChanged(propertyReference)

        if (propertyReference == PROPERTY_CONTENT) {
            this.handleContentPropertyChanged()
        }
        else if (propertyReference == PROPERTY_LEFT_VIEWS) {
            this.handleLeftViewsObservableChanged()
        }
        else if (propertyReference == PROPERTY_RIGHT_VIEWS) {
            this.handleRightViewsObservableChanged()
        }
        else if (propertyReference == PROPERTY_LEFT_BUTTONS) {
            this.handleLeftButtonsObservableChanged()
        }
        else if (propertyReference == PROPERTY_RIGHT_BUTTONS) {
            this.handleRightButtonsObservableChanged()
        }
    }

    private void handleContentPropertyChanged() {
        assert Platform.isFxApplicationThread()

        def content = this.skinnable.contentProperty().get()
        if (content) {
            this.contentContainer.children.setAll(content)
        }
    }

    private void handleLeftViewsObservableChanged() {
        assert Platform.isFxApplicationThread()

        def leftViews = this.skinnable.leftViews
        this.leftContainer.items.setAll(leftViews)
    }

    private void handleRightViewsObservableChanged() {
        assert Platform.isFxApplicationThread()

        def rightViews = this.skinnable.rightViews
        this.rightContainer.items.setAll(rightViews)
    }

    private void handleLeftButtonsObservableChanged() {
        assert Platform.isFxApplicationThread()

        def leftButtons = this.skinnable.leftButtons
        this.leftToolBar.items.setAll(leftButtons)
    }

    private void handleRightButtonsObservableChanged() {
        assert Platform.isFxApplicationThread()

        def rightButtons = this.skinnable.rightButtons
        this.rightToolBar.items.setAll(rightButtons)
    }

    protected void layoutChildren(double x,
                                  double y,
                                  double width,
                                  double height) {
        super.layoutChildren(x, y, width, height)
    }

    private void registerListChangeListener(ObservableList<?> observableList,
                                            String reference) {
        observableList.addListener({ change ->
            this.handleControlPropertyChanged(reference)
        } as ListChangeListener<?>)
    }

}

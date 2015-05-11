package de.entera.workbenchfx

import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.ToggleButton
import javafx.stage.Stage

import org.junit.Test
import org.testfx.framework.junit.ApplicationTest

import static org.hamcrest.Matchers.equalTo
import static org.testfx.api.FxAssert.verifyThat
import static org.testfx.matcher.base.NodeMatchers.hasChild

class WorkbenchTest extends ApplicationTest {

    //---------------------------------------------------------------------------------------------
    // FIELDS.
    //---------------------------------------------------------------------------------------------

    Workbench workbench

    //---------------------------------------------------------------------------------------------
    // FIXTURE METHODS.
    //---------------------------------------------------------------------------------------------

    void start(Stage stage) {
        workbench = new Workbench()

        def scene = new Scene(workbench, 800, 600)
        stage.scene = scene
        stage.show()
    }

    //---------------------------------------------------------------------------------------------
    // FEATURE METHODS.
    //---------------------------------------------------------------------------------------------

    @Test
    void "should have style class .workbench"() {
        // expect:
        verifyThat(".workbench", equalTo(workbench))
    }

    @Test
    void "should update content"() {
        // when:
        interact {
            workbench.content = new Label("content")
        }

        // then:
        verifyThat(".workbench .content.container", hasChild("content"))
    }

    @Test
    void "should update left views"() {
        // when:
        interact {
            workbench.leftViews << new Label("left one") << new Label("left two")
        }

        // then:
        verifyThat(".workbench .left.container", hasChild("left one"))
        verifyThat(".workbench .left.container", hasChild("left two"))
    }

    @Test
    void "should update right views"() {
        // when:
        interact {
            workbench.rightViews << new Label("right one") << new Label("right two")
        }

        // then:
        verifyThat(".workbench .right.container", hasChild("right one"))
        verifyThat(".workbench .right.container", hasChild("right two"))
    }

    @Test
    void "should update left buttons"() {
        // when:
        interact {
            workbench.leftButtons << new ToggleButton("left first button")
        }

        // then:
        verifyThat(".workbench .left.tool-bar", hasChild("left first button"))
    }

    @Test
    void "should update right buttons"() {
        // when:
        interact {
            workbench.rightButtons << new ToggleButton("right first button")
        }

        // then:
        verifyThat(".workbench .right.tool-bar", hasChild("right first button"))
    }

}

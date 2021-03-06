package strongdmm.ui.panel.instance_locator

import strongdmm.PostInitialize
import strongdmm.Processable
import strongdmm.Ui

class InstanceLocatorPanelUi : Ui, Processable, PostInitialize {
    private val state = State()
    private val view = View(state)
    private val viewController = ViewController(state)
    private val eventController = EventController(state)

    init {
        view.viewController = viewController
        eventController.viewController = viewController
    }

    override fun postInit() {
        eventController.postInit()
    }

    override fun process() {
        view.process()
    }
}

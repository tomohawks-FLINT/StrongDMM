package strongdmm.ui.tile_popup

import strongdmm.Processable
import strongdmm.Ui

class TilePopupUi : Ui, Processable {
    private val state = State()
    private val view = View(state)
    private val viewController = ViewController(state)
    private val eventController = EventController(state)

    init {
        view.viewController = viewController
        eventController.viewController = viewController
    }

    override fun process() {
        view.process()
    }
}

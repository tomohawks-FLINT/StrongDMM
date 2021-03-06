package strongdmm.service

import strongdmm.Service
import strongdmm.byond.dmm.TileItem
import strongdmm.event.Event
import strongdmm.event.EventHandler
import strongdmm.event.type.Reaction
import strongdmm.event.type.service.TriggerTileItemService

class TileItemService : Service, EventHandler {
    private var selectedTileItem: TileItem? = null

    init {
        consumeEvent(TriggerTileItemService.ChangeSelectedTileItem::class.java, ::handleChangeSelectedTileItem)
        consumeEvent(TriggerTileItemService.ResetSelectedTileItem::class.java, ::handleResetSelectedTileItem)
        consumeEvent(Reaction.EnvironmentReset::class.java, ::handleEnvironmentReset)
    }

    private fun handleChangeSelectedTileItem(event: Event<TileItem, Unit>) {
        selectedTileItem = event.body
        sendEvent(Reaction.SelectedTileItemChanged(event.body))
    }

    private fun handleResetSelectedTileItem() {
        selectedTileItem = null
        sendEvent(Reaction.SelectedTileItemChanged(null))
    }

    private fun handleEnvironmentReset() {
        selectedTileItem = null
        sendEvent(Reaction.SelectedTileItemChanged(null))
    }
}

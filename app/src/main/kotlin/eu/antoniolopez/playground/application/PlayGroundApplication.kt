package eu.antoniolopez.playground.application

import eu.antoniolopez.playground.core.di.coreComponent
import eu.antoniolopez.playground.core.view.BaseApplication
import org.kodein.di.Kodein

class PlayGroundApplication : BaseApplication(){
    override val kodein = Kodein.lazy {
        extend(coreComponent)
    }
}

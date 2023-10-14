package engine.ecs.node.root

import engine.ecs.component.Camera
import engine.ecs.node.GNode

class Root: GNode() {
    val camera = addComponent(Camera())
}
package playground

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.image.{ImageView, WritableImage}
import swiftvis2.raytrace.LinearViewPath.View
import swiftvis2.raytrace._

object KDTreeShadowTesting extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Ray Trace Test"
    scene = new Scene(720, 480) {
      val img = new WritableImage(720, 480)
      content = List(new ImageView(img))

      val spheres = Array(
          GeomSphere(swiftvis2.raytrace.Point(0, 0, 0), 1, p => RTColor.White, p => 0.0),
          GeomSphere(swiftvis2.raytrace.Point(0.5, -2, 0), 0.5, p => RTColor.White, p => 0.0),
          GeomSphere(swiftvis2.raytrace.Point(0, 0, 2), 0.5, p => RTColor.White, p => 0.0))
      val tree = new KDTreeGeometry(spheres, 1)
      val view = View(swiftvis2.raytrace.Point(-1, -5, 0), Vect(1, 5, 0).normalize, Vect(0, 0, 1))
      val lights = List(
          AmbientLight(RTColor(0.1, 0.1, 0.1)),
          DirectionLight(RTColor(1.0, 0.2, 0.0), Vect(0, 0, -1)),
          PointLight(RTColor.Blue, swiftvis2.raytrace.Point(0.5, -10, 0.5)))
      RayTrace.render(view, new FXRTImage(img), tree, lights, 1)
    }
  }
}

package tests

import org.scalatest.FunSuite
import store.model.items.Item

class LectureTask1 extends FunSuite {

  val epsilon = 0.01

  def compareDoubles (input1: Double, input2: Double): Boolean = {
    math.abs(input1 - input2) < epsilon
  }

  test("Test 1") {

    val TestItem: Item = new Item("apple", 1.5)
    TestItem.scanned() // Scan item once
    TestItem.scanned()

    assert(compareDoubles(TestItem.price(),1.5))
    assert(TestItem.description() == "apple")
    assert(TestItem.timesScanned() == 2)

  }

}

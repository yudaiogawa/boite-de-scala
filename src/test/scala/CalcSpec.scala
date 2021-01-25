import org.scalatest._
import org.scalatest.concurrent.Timeouts
import org.scalatest.time.SpanSugar.convertIntToGrainOfTime
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._

class CalcSpec extends FlatSpec with DiagrammedAssertions with Timeouts with MockitoSugar {

  val sut = new Calc()

  "sum function" should "Standard" in {
    assert(sut.sum(Seq(0, 2, 4)) === 6)
    assert(sut.sum(Seq()) === 0)
  }

  it should "Overflow when over the max integer" in {
    assert(sut.sum(Seq(Integer.MAX_VALUE, 1)) === Integer.MIN_VALUE)
  }

  "div function" should "Standard" in {
    assert(sut.div(4, 2) === 2.0)
  }

  it should "Exception when divide by zero" in {
    intercept[ArithmeticException] {
      sut.div(4, 0)
    }
  }

  "isPrimeNumber function" should "Standard" in {
    assert(sut.isPrimeNumber(-1) === false)
    assert(sut.isPrimeNumber(0) === false)
    assert(sut.isPrimeNumber(1) === false)
    assert(sut.isPrimeNumber(2))
    assert(sut.isPrimeNumber(11))
  }

  it should "time" in {
    failAfter(1000.millis) {
      assert(sut.isPrimeNumber(999999) === false)
    }
  }

  "Mocking Calc" should "Mocking" in {
    val mockCalc = mock[Calc]
    when(mockCalc.sum(Seq(2, 4, 8))).thenReturn(14)
    assert(mockCalc.sum(Seq(2, 4, 8)) === 14)
  }
}


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SampleTest {

    @Test
    void 성공_일더하기일은2이다() {
        Assertions.assertThat(1+1).isEqualTo(2);
    }

    // 우리 입장에서 빨간 불이 들어오면 성공임.
    // 왜냐면 우리는 실패하는 걸 인지하고 있으니까.
    @Test
    void 실패_일더하기일은3이다() {
        Assertions.assertThat(1+1).isEqualTo(3);
    }
}

package life.icetea.test.mapstruct;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author icetea
 */
public class TestCarMapper {

    @Test
    public void test() {
        //given
        Car car = new Car();
        car.setMake("Morris");
        car.setNumberOfSeats(5);
        car.setType(CarType.SEDAN);
        //when
        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);

        //then
        Assert.assertNotNull(carDto);
        Assert.assertEquals(carDto.getMake(), "Morris");
        Assert.assertEquals(carDto.getSeatCount(), 5);
        Assert.assertEquals(carDto.getType(), "SEDAN");
    }

}

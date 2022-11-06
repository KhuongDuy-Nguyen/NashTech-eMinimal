import Carousel from 'react-bootstrap/Carousel';
import banner1 from '../../assets/images/banner/banner_1.jpg';
import banner2 from '../../assets/images/banner/banner_2.jpg';

function Banner(){
    return (
      <>
        <Carousel>
          <Carousel.Item>
            <img
              src={banner1}
              className="d-block w-100"
              alt="..."
            />
          </Carousel.Item>

          <Carousel.Item>
            <img
              src={banner2}
              className="d-block w-100"
              alt="..."
            />
          </Carousel.Item>


        </Carousel>
      </>
    );
}

export default Banner
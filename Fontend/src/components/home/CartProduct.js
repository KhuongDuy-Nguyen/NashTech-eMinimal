import { Button, InputNumber, Row } from "antd";
import { Card, Col } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import images from "../../assets/images/sofa-sell.png";
import { removeProductFromCart } from "../../services/cart";
import price from "../../utils/priceWithCommas";



export default function CartProduct(props) {
  const handleDeleteProduct = (id) => {
    removeProductFromCart(id, localStorage.getItem("cartId"))
      .then((res) => {
        console.log(res);
        props.quantity -= 1;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const handleChangeValue = (value) => {
    props.quantity = value;
  };

  return (
    <Card className="m-3">
      <Row>
        <Col xs={3}>
          <Card.Img src={images} style={{ height: "120px", width: "120px" }} />
        </Col>
        <Col xs={8}>
          <Card.Body className="h-100 w-100 text-center p-4">
            <Card.Title>{props.name}</Card.Title>
            {/* Button remove product */}

            <Row className="">
              <Col xs={4} className="text-center position-relative">
                <Card.Text className="position-absolute top-50 start-50 translate-middle fs-6">
                  Quantity
                </Card.Text>
              </Col>

              <Col xs={4}>
                <InputNumber
                  min={1}
                  max={10}
                  value={props.quantity}
                  onChange={handleChangeValue}
                />
              </Col>

              <Col xs={4} className="text-center position-relative">
                <Card.Text className="position-absolute top-50 start-50 translate-middle fs-6">
                  ${price(props.price)}
                </Card.Text>
              </Col>
            </Row>
          </Card.Body>
        </Col>
        <Col xs={1}>
          <Button
            type="primary"
            className="h-100"
            danger
            onClick={() => handleDeleteProduct(props.productID)}
          >
            <i class="fa-solid fa-trash"></i>
          </Button>
        </Col>
      </Row>
    </Card>
  );
}

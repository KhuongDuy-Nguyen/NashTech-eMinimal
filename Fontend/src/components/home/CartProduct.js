import { InputNumber, Row } from "antd";
import { Card, Col } from "react-bootstrap";
import images from "../../assets/images/sofa-sell.png";

export default function CartProduct(props) {
  return (
    <Card className="m-3">
      <Row>
        <Col xs={3}>
          <Card.Img src={images} style={{ height: "120px", width: "120px" }} />
        </Col>
        <Col xs={8}>
          <Card.Body className="h-100 w-100 text-center p-4">
            <Card.Title>{props.name}</Card.Title>
            <Row className="">
              <Col xs={4} className="text-center position-relative">
                <Card.Text className="position-absolute top-50 start-50 translate-middle fs-6">
                  Quantity
                </Card.Text>
              </Col>

              <Col xs={4}>
                <InputNumber min={1} max={10} defaultValue={props.quantity} />
              </Col>

              <Col xs={4} className="text-center position-relative">
                <Card.Text className="position-absolute top-50 start-50 translate-middle fs-6">
                  ${props.price}
                </Card.Text>
              </Col>
            </Row>
          </Card.Body>
        </Col>
      </Row>
    </Card>
  );
}

import React ,{useState} from "react";
import { useEffect } from "react";
import { Rate } from 'antd';
import style from "../../styles/product.module.css";
import sofa_sell from "../../assets/images/sofa-sell.png";
import { Button, Card, Col, Modal, Row } from "react-bootstrap";
import Carousel from "react-bootstrap/Carousel";
import { useParams } from "react-router-dom";


function ProductItem() {
  const [product, setProduct] = useState([]);

  // Make default value in usePrams

  
  
  // const [filter, setFilter] = useState("all");
  // const [category, setCategory] = useState("all");

  var { categoryName } = useParams();
  var { filter } = useParams();

  if(categoryName === undefined){
    categoryName = "all";
  }

  if (filter === undefined) {
    filter = "all";
  }


  useEffect(() => {
    if (categoryName === "all") {
      fetch("http://localhost:8080/api/product/all")
        .then((res) => res.json())
        .then((data) => {
          setProduct(data);
        });
    }
    fetch(`http://localhost:8080/api/product/category?name=${categoryName}`)
      .then((res) => res.json())
      .then((data) => {
        setProduct(data);
      });
  }, [categoryName]);

  useEffect(() => {
    fetch(`http://localhost:8080/api/product/${filter}`)
      .then((res) => res.json())
      .then((data) => {
        setProduct(data);
      });
  }, [filter]);

  

  // For modal and transfer data to modal
  const [show, setShow] = useState(false);
  const [data, setData] = useState({});

  const handleClose = () => setShow(false);
  const handleShow = (data) => {
    setData(data);
    setShow(true);
  };

  // For Carousel
  const [index, setIndex] = useState(0);

  const handleSelect = (selectedIndex, e) => {
    setIndex(selectedIndex);
  };

  return (
    <div id="product_item">
      <Row>
        {product.map((val) => {
          return (
            <Col className="col-4 mb-4" key={val.productID}>
              <Card>
                <Card.Img
                  src={sofa_sell}
                  alt=""
                  onClick={() => handleShow(val)}
                />
                <Card.Body className={style.item_info}>
                  <Row className="">
                    <div className="col-7 pt-2">
                      <Card.Text className={style.item_info_p}>
                        {val.productName}
                      </Card.Text>

                      <Card.Text className={style.item_info_p}>
                        ${val.productCost}
                      </Card.Text>
                    </div>

                    <div className="col-5">
                      <div className="mb-3">
                        <span>
                          <Rate disabled defaultValue={5} />
                        </span>
                      </div>

                      <div id="btn">
                        <Button>
                          <i className="fa-light fa-cart-plus fa-xl"></i> Add
                          cart
                        </Button>
                      </div>
                    </div>
                  </Row>
                </Card.Body>

                <Card.Text className={style.item_sale_p}>
                  {val.details.productSale}% off
                </Card.Text>
              </Card>
            </Col>
          );
        })}
      </Row>

      {/* Modal */}
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Information</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Card>
            <Carousel activeIndex={index} onSelect={handleSelect}>
              <Carousel.Item>
                <img
                  className="d-block w-100"
                  src={sofa_sell}
                  alt="First slide"
                />
              </Carousel.Item>

              <Carousel.Item>
                <img
                  className="d-block w-100"
                  src={sofa_sell}
                  alt="Second slide"
                />
              </Carousel.Item>

              <Carousel.Item>
                <img
                  className="d-block w-100"
                  src={sofa_sell}
                  alt="Third slide"
                />
              </Carousel.Item>
            </Carousel>
          </Card>
          <br />
          <div className="row">
            <div className="col-6">
              <h4>Name</h4>
              <p>{data.productName}</p>
              <h4>Description</h4>
              <p>{data.productDesc}</p>
            </div>
            <div className="col-6">
              <div className="mb-3">
                <h4>Price</h4>
                <p>${data.productCost}</p>

                <span>
                  <Rate defaultValue={5} />
                </span>
              </div>
            </div>
          </div>
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Add to cart
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default ProductItem;
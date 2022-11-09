import React, { useState } from "react";
import { useEffect } from "react";
import { message, Rate } from "antd";
import style from "../../styles/product.module.css";
import sofa_sell from "../../assets/images/sofa-sell.png";
import { Button, Card, Col, Modal, Row } from "react-bootstrap";
import Carousel from "react-bootstrap/Carousel";
import { useParams } from "react-router-dom";

import ErrorAuth from "../../utils/errorAuth";
import Pagination from "./Pagination";
import axios from "axios";
import {
  getAllProducts,
  getProductsByCategory,
  productRating,
  searchProduct,
} from "../../services/product";
import AddToCart from "../../services/addToCart";
import checkLogin from "../../utils/checkLogin";
import ShowMessage from "../../utils/message";
import logo from "../../assets/images/logo.jpg";
import price from "../../utils/priceWithCommas";

function ProductItem() {
  const [product, setProduct] = useState([]);
  const [rating, setRating] = useState(false);
  // Make default value in usePrams

  // const [filter, setFilter] = useState("all");
  // const [category, setCategory] = useState("all");

  var { categoryName } = useParams();
  var { filter } = useParams();
  var { searchName } = useParams();

  // Get all product
  useEffect(() => {
    getAllProducts()
      .then((res) => {
        setProduct(res.data);
      })
      .catch((err) => {
        console.log(err);
        ErrorAuth(err);
      });
  }, []);

  // Filter product by sale or featured
  useEffect(() => {
    if (filter !== undefined && filter !== "all") {
      axios
        .get(`http://localhost:8080/api/product/${filter}`)
        .then((res) => {
          setProduct(res.data);
        })
        .catch((err) => {
          console.log(err);
          ErrorAuth(err);
        });
    }
  }, [filter]);

  // Filter product by category
  useEffect(() => {
    if (categoryName !== "all" && categoryName !== undefined) {
      getProductsByCategory(categoryName)
        .then((res) => {
          setProduct(res.data);
        })
        .catch((err) => {
          console.log(err);
          ErrorAuth(err);
        });
    }
  }, [categoryName]);

  // Filter product by search
  // TODO: Search product by name - BUG
  useEffect(() => {
    console.log(searchName);
    if (searchName !== undefined) {
      searchProduct(searchName)
        .then((res) => {
          setProduct(res.data);
        })
        .catch((err) => {
          console.log(err);
          ErrorAuth(err);
        });
    } else {
      getAllProducts()
        .then((res) => {
          setProduct(res.data);
        })
        .catch((err) => {
          console.log(err);
          ErrorAuth(err);
        });
    }
  }, [searchName]);

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

  // Add to cart
  const handleAddToCart = (productID) => {
    console.log(productID);
    AddToCart(productID);
  };

  // Rating
  const handleRating = (value, productID) => {
    if (checkLogin) {
      productRating(productID, localStorage.getItem("userId"), value)
        .then((res) => {
          ShowMessage("success", "Rating success. Thank you for your feedback");
          setRating(!rating);
        })
        .catch((err) => {
          ErrorAuth(err);
        });
    }
  };

  const calculateRating = (product) => {
    if (product.length > 0) {
      let total = 0;
      product.map((item) => {
        total += item.rating;
      });
      return total / product.length;
    } else {
      return 5;
    }
  };

  const renderImages = (val) => {
    // console.log(val.productName);
    // console.log(val.productImage);
    console.log(val);
    if (val.productImage.length === 0) {
      return logo;
    }else{
      var url = val.productImage[0];
      console.log("LIST URL");
      product.map((item) => {
        console.log(item.productImage);
      });
      // val.productImage.map((item) => {
      //   console.log(item);
      // });
      return url.replace("?dl=0", "?raw=1");
    }
  };

  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize, setPageSize] = useState(9);

  const lastProductIndex = currentPage * pageSize;
  const firstProductIndex = lastProductIndex - pageSize;

  const currentProduct = product.slice(firstProductIndex, lastProductIndex);

  return (
    <>
      <div id="product_item">
        <Row>
          {currentProduct.map((val) => {
            return (
              <Col className="col-4 mb-4" key={val.productID}>
                <Card>
                  <Card.Img
                    src={renderImages(val)}
                    alt=""
                    style={{
                      height: "300px",
                      objectFit: "cover",
                      cursor: "pointer",
                      width: "415px",
                    }}
                    onClick={() => {
                      handleShow(val);
                    }}
                  />
                  <Card.Body className={style.item_info}>
                    <Row className="">
                      <div className="col-7 pt-2">
                        <Card.Text className={style.item_info_p}>
                          {val.productName}
                        </Card.Text>

                        <Card.Text className={style.item_info_p}>
                          <div className="text-decoration-line-through">
                            <p>${(val.productCost)}</p>
                          </div>
                          <div
                            style={{
                              display: "inline",
                              color: "red",
                            }}
                          >
                            $
                            {(
                              Math.round(
                                val.productCost -
                                  val.productCost * (val.productSale / 100)
                              )
                            )}
                          </div>
                        </Card.Text>
                      </div>

                      <div className="col-5">
                        <div className="mb-3">
                          <span>
                            <Rate
                              disabled
                              defaultValue={calculateRating(val.productRating)}
                            />
                          </span>
                        </div>

                        <div id="btn">
                          <Button
                            onClick={() => {
                              handleAddToCart(val.productID);
                            }}
                          >
                            <i className="fa-light fa-cart-plus fa-xl"></i> Add
                            cart
                          </Button>
                        </div>
                      </div>
                    </Row>
                  </Card.Body>

                  <Card.Text className={style.item_sale_p}>
                    {val.productSale}% off
                  </Card.Text>
                </Card>
              </Col>
            );
          })}
        </Row>

        {/* Modal */}
        <Modal show={show} onHide={handleClose} style={{ zIndex: 10040 }}>
          <Modal.Header closeButton>
            <Modal.Title>Information</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Card>
              <Carousel activeIndex={index} onSelect={handleSelect}>
                {product.map((val) => {
                  if (val.productID === data.productID) {
                    return val.productImage.map((item) => {
                      return (
                        <Carousel.Item key={item}>
                          <img
                            className="d-block w-100"
                            src={item.replace("?dl=0", "?raw=1")}
                            alt="First slide"
                            style={{
                              height: "300px",
                              objectFit: "cover",
                              width: "415px",
                            }}
                          />
                        </Carousel.Item>
                      );
                    });
                  }
                })}
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
                  <p>${(data.productCost)}</p>
                  <h4>Rate</h4>
                  <span>
                    <Rate
                      defaultValue={5}
                      onChange={(value) => {
                        handleRating(value, data.productID);
                      }}
                    />
                  </span>
                </div>
              </div>
            </div>
          </Modal.Body>

          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            <Button
              variant="primary"
              onClick={() => {
                handleAddToCart(data.productID);
              }}
            >
              Add to cart
            </Button>
          </Modal.Footer>
        </Modal>
      </div>

      <Pagination
        totalProduct={product.length}
        productPerPage={pageSize}
        setCurrentPage={setCurrentPage}
        currentPage={currentPage}
      />
    </>
  );
}

export default ProductItem;

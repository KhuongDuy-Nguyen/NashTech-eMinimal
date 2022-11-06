import { Col, Row } from 'react-bootstrap';
import style from '../../styles/policy.module.css';

function Policy() {
  return (
    <Row id="policy" className='mt-5'>
      <Col md={3}>
        <Row>
          <div className="col-2 align-self-center">
            <i className="fa-regular fa-truck-fast fa-2xl"></i>
          </div>
          <div className="col">
            <h6 className={style.policy_item_h6}>Free Shipping</h6>
            <p className={style.policy_item_p}>Order over $100</p>
          </div>
        </Row>
      </Col>

      <Col md={3}>
        <Row className={style.policy_item}>
          <div className="col-2 align-self-center">
            <i className="fa-regular fa-phone fa-2xl"></i>
          </div>
          <div className="col">
            <h6 className={style.policy_item_h6}>24/7 Support Help</h6>
            <p  className={style.policy_item_p}>Dedicated Support</p>
          </div>
        </Row>
      </Col>

      <Col md={3}>
        <Row className={style.policy_item}>
          <div className="col-2 align-self-center">
            <i className="fa-regular fa-shield-check fa-2xl"></i>
          </div>
          <div className="col">
            <h6 className={style.policy_item_h6}>Secure Payment</h6>
            <p className={style.policy_item_p}>100% Secure & Safe</p>
          </div>
        </Row>
      </Col>

      <Col md={3}>
        <Row className={style.policy_item}>
          <div className="col-2 align-self-center">
            <i className="fa-regular fa-circle-dollar fa-2xl"></i>
          </div>
          <div className="col">
            <h6 className={style.policy_item_h6}>90 Days Return</h6>
            <p className={style.policy_item_p}>For goods issues</p>
          </div>
        </Row>
      </Col>

    </Row>
    
  );
}

export default Policy;
import { Col, Row } from 'react-bootstrap';
import style from '../../styles/category.module.css';

import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button } from 'antd';
import { Link } from 'react-router-dom';


function Category() {

  const [APIData, setAPIData] = useState([]);
  const [category, setCategory] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/category/all`).then((response) => {
      setAPIData(response.data);
    });
  }, []);

  return (
    <div className="mt-5 mb-4" id="category">
      <h3 className={style.category_title_h3}>shop by category</h3>

      <div className="category_item mt-4">
        <Row>
          {APIData.map((data) => {
            return (
              <Col className="m-3 text-center" key={data.categoryID}>
                <Button>
                  <Link to={`/category/${data.categoryName}`}>
                    {data.categoryName}
                  </Link>
                </Button>
              </Col>
            );
          })}
        </Row>
      </div>
    </div>
  );
}

export default Category;
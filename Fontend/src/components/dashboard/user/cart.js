import { Button, Space, Table, Form, Input, Modal, Tag } from "antd";
import { Content } from "antd/lib/layout/layout";
import React, { useState, useEffect } from "react";
import { deleteCart, getAllCartByUserId } from "../../../services/cart.js";

import {
  getAllCategory,
  createCategory,
  updateCategory,
  deleteCategory,
} from "../../../services/category.js";
import ShowMessage from "../../../utils/message";
import price from "../../../utils/priceWithCommas.js";

function App() {
  // Load data
  const [data, setData] = useState([]);

  // Id of category to edit
  const [id, setId] = useState(0);

  useEffect(() => {
    getData();
  }, []);

  const getData = () =>
    getAllCartByUserId(localStorage.getItem("userId"))
      .then((res) => {
        console.log(res);
        setData(
          res.data.map((item) => {
            console.log(item.cartStatus);
            return {
              key: item.cartID,
              quantity: item.cartQuantity,
              price: item.price,
              status: item.cartStatus ? "Paid" : "Unpaid",
            };
          })
        );
      })
      .catch((err) => {
        console.log(err);
      });

  const [selectedRowKeys, setSelectedRowKeys] = useState([]);

  const onSelectChange = (newSelectedRowKeys) => {
    console.log("selectedRowKeys changed: ", newSelectedRowKeys);
    setSelectedRowKeys(newSelectedRowKeys);
  };

  const rowSelection = {
    selectedRowKeys,
    onChange: onSelectChange,
  };

  const hasSelected = selectedRowKeys.length > 0;

  //   For modal
  const [openDelete, setOpenDelete] = useState(false);

  const success = (mes) => {
    ShowMessage("success", `${mes} success`);
  };

  const error = (mes) => {
    ShowMessage("error", `${mes}`);
  };

  const handleDelete = (e) => {
    setOpenDelete(true);
    setId(e.key);
  };

  const handleDeleteOk = () => {
    setOpenDelete(false);
    console.log("Run delete");
    deleteCart(id)
      .then((res) => {
        console.log(res);
        getData();
        success("Delete cart");
      })
      .catch((err) => {
        // console.log(err);
        error(err.response.data.message);
      });
  };

  // Column table
  const columns = [
    {
      title: "Quantity",
      dataIndex: "quantity",
    },
    {
      title: "Price ($)",
      dataIndex: "price",
      render: (text) => price(text),
    },
    {
      title: "Status",
      dataIndex: "status",
      render: (status) => {
        let color = status === "Paid" ? "green" : "volcano";
        return (
          <Tag color={color} key={status}>
            {status.toUpperCase()}
          </Tag>
        );
      },
    },
    {
      title: "Action",
      key: "key",
      render: (key) => (
        <Space size="middle">
          <Button
            type="primary"
            danger
            onClick={() => {
              handleDelete(key);
            }}
          >
            Delete
          </Button>
        </Space>
      ),
    },
  ];

  return (
    <>
      <Content
        className="site-layout-background"
        style={{
          margin: "24px 16px",
          padding: 24,
          minHeight: 280,
        }}
      >
        <div>
          <Table
            rowSelection={rowSelection}
            columns={columns}
            dataSource={data}
          />
        </div>
      </Content>

      {/* Modal delete */}
      <Modal
        title="Delete Cart"
        open={openDelete}
        okText="Delete"
        onCancel={() => {
          setOpenDelete(false);
        }}
        footer={null}
      >
        <p>Are you sure to delete this cart?</p>
        <Button
          type="primary"
          danger
          onClick={() => {
            handleDeleteOk();
          }}
        >
          Delete
        </Button>
      </Modal>
    </>
  );
}
export default App;

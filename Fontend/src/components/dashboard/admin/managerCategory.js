import { Button, Space, Table, Form, Input, Modal, message } from "antd";
import { Content } from "antd/lib/layout/layout";
import axios from "axios";
import React, { useState, useEffect } from "react";
import ErrorAuth from "../../../utils/errorAuth";

import {
  getAllCategory,
  createCategory,
  updateCategory,
  deleteCategory,
} from "../../../services/category.js";
import ShowMessage from "../../../utils/message";

function App() {
  // Load data
  const [data, setData] = useState([]);

  // Id of category to edit
  const [id, setId] = useState(0);

  useEffect(() => {
    getData();
  }, []);

  const getData = () =>
    getAllCategory()
      .then((res) => {
        // console.log(res);
        setData(
          res.data.map((item) => {
            return {
              key: item.categoryID,
              name: item.categoryName,
              desc: item.categoryDesc,
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
  const [openCreate, setOpenCreate] = useState(false);
  const [openUpdate, setOpenUpdate] = useState(false);
  const [openDelete, setOpenDelete] = useState(false);

  // Form
  const [form] = Form.useForm();

  const success = (mes) => {
    ShowMessage("success",`${mes} success`);
  };

  const error = (mes) => {
    ShowMessage("error",`${mes}`);
  };

  const onFinishUpdate = (values) => {
    updateCategory(id, values)
      .then((res) => {
        success("Update");
        console.log(res);
        getData();
      })
      .catch((err) => {
        // console.log(err.response.data.message);
        error(err.response.data.message);
      });

    setOpenUpdate(false);
  };

  const onFinishCreate = (values) => {
    createCategory(values)
      .then((res) => {
        getData();
        success("Create");
        form.resetFields();
      })
      .catch((err) => {
        error(err.response.data.message);
      });

    setOpenCreate(false);
  };

  const handleUpdate = (e) => {
    setId(e.key);
    form.setFieldsValue({
      categoryName: e.name,
      categoryDesc: e.desc,
    });
    setOpenUpdate(true);
  };

  const handleDelete = (e) => {
    setOpenDelete(true);
    setId(e.key);
  };

  const handleDeleteOk = () => {
    setOpenDelete(false);
    console.log("Run delete");
    deleteCategory(id)
      .then((res) => {
        console.log(res);
        getData();
        success("Delete");
      })
      .catch((err) => {
        // console.log(err);
        error(err.response.data.message);
      });
  };

  // Column table
  const columns = [
    {
      title: "Name",
      dataIndex: "name",
    },
    {
      title: "Description",
      dataIndex: "desc",
    },
    {
      title: "Action",
      key: "key",
      render: (key) => (
        <Space size="middle">
          <Button
            type="primary"
            onClick={() => {
              // take id when click edit
              handleUpdate(key);
            }}
          >
            Edit
          </Button>
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
          <div
            style={{
              marginBottom: 16,
            }}
          >
            <Button
              type="primary"
              onClick={() => {
                setOpenCreate(true);
              }}
            >
              New
            </Button>
            <span
              style={{
                marginLeft: 8,
              }}
            >
              {hasSelected ? `Selected ${selectedRowKeys.length} items` : ""}
            </span>
          </div>
          <Table
            rowSelection={rowSelection}
            columns={columns}
            dataSource={data}
          />
        </div>
      </Content>

      {/* Modal create */}
      <Modal
        title="Create Category"
        open={openCreate}
        // onOk={handleOk}
        okText="Create"
        onCancel={() => {
          setOpenCreate(false);
        }}
        footer={null}
      >
        <Form
          form={form}
          labelCol={{ span: 6 }}
          wrapperCol={{ span: 16 }}
          onFinish={onFinishCreate}
          autoComplete="off"
        >
          <Form.Item
            label="Name"
            name="categoryName"
            rules={[
              { required: true, message: "Please input your category name!" },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="Description"
            name="categoryDesc"
            rules={[
              {
                required: true,
                message: "Please input your category description!",
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            wrapperCol={{
              offset: 6,
              span: 16,
            }}
          >
            <Button type="primary" htmlType="submit">
              Create
            </Button>
          </Form.Item>
        </Form>
      </Modal>

      {/* Modal update */}
      <Modal
        title="Update Category"
        open={openUpdate}
        okText="Save"
        onCancel={() => {
          setOpenUpdate(false);
        }}
        footer={null}
      >
        <Form
          form={form}
          labelCol={{ span: 6 }}
          wrapperCol={{ span: 16 }}
          onFinish={onFinishUpdate}
          autoComplete="off"
        >
          <Form.Item
            label="Name"
            name="categoryName"
            rules={[
              { required: true, message: "Please input your category name!" },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="Description"
            name="categoryDesc"
            rules={[
              {
                required: true,
                message: "Please input your category description!",
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            wrapperCol={{
              offset: 6,
              span: 16,
            }}
          >
            <Button type="primary" htmlType="submit">
              Save
            </Button>
          </Form.Item>
        </Form>
      </Modal>

      {/* Modal delete */}
      <Modal
        title="Delete Category"
        open={openDelete}
        okText="Delete"
        onCancel={() => {
          setOpenDelete(false);
        }}
        footer={null}
      >
        <p>Are you sure to delete this category?</p>
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

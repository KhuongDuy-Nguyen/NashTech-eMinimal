import {
  Button,
  DatePicker,
  Form,
  Input,
  InputNumber,
  message,
  Modal,
  Select,
  Space,
  Table,
  Upload,
} from "antd";
import { PlusOutlined } from "@ant-design/icons";
import { Content } from "antd/lib/layout/layout";
import React, { useState } from "react";
import { useEffect } from "react";
import ErrorAuth from "../../../utils/errorAuth";
import {
  createProduct,
  deleteProduct,
  getAllProducts,
  productUploadImages,
  updateProduct,
} from "../../../services/product";
import { getAllCategory } from "../../../services/category";
import ShowMessage from "../../../utils/message";

const { RangePicker } = DatePicker;

const App = () => {
  const [data, setData] = useState([]);
  const [category, setCategory] = useState([]);

  const [categoryName, setCategoryName] = useState("");

  // Id of product to edit
  const [id, setId] = useState(0);
  const [name, setName] = useState("");


  useEffect(() => {
    getData();
  }, []);

  const getData = () =>
    getAllProducts()
      .then((res) => {
        console.log(res.data);
        setData(
          res.data.map((item) => {
            return {
              key: item.productID,
              name: item.productName,
              desc: item.productDesc,
              cost: item.productCost,
              sale: item.productSale,
              category: item.categories.categoryName,
              amount: item.productAmount,
              dateCreated: item.dateCreate,
            };
          })
        );
      })
      .catch((err) => {
        ErrorAuth(err);
      });

  // Get categories
  getAllCategory()
    .then((res) => {
      setCategory(
        res.data.map((item) => {
          return {
            label: item.categoryName,
            value: item.categoryName,
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

  const onFinishCreate = (values) => {
    createProduct(values)
      .then((res) => {
        console.log(res);
        getData();
        ShowMessage("success","Create product success");
      })
      .catch((err) => {
        ErrorAuth(err);
      });

    console.log(values);
    form.resetFields();
    setOpenCreate(false);
  };

  const uploadFile = (values, id) => {
    if (values.productImages.fileList.length > 0) {
      for (var i = 0; i < values.productImages.fileList.length; i++) {
        console.log(values.productImages.fileList[i].originFileObj);
        let formData = new FormData();
        formData.append("file", values.productImages.fileList[i].originFileObj);
        formData.append("id", id);
        productUploadImages(formData)
          .then((res) => {
            ShowMessage("success","Upload image success");
          }).catch((err) => {
            console.log(err);
            ShowMessage("error",err.response.data.message);
          });
      }
    }
  };

  const onFinishUpdate = (values) => {
    updateProduct(id, values)
      .then((res) => {
        console.log(res);
        getData();
        uploadFile(values, id);
        ShowMessage("success","Update info successfully");
      })
      .catch((err) => {
        ErrorAuth(err);
      });
    form.resetFields();
    setOpenUpdate(false);
  };

  const handleUpdate = (e) => {
    setId(e.key);
    console.log(e);
    form.setFieldsValue({
      productName: e.name,
      productDesc: e.desc,
      productCost: e.cost,
      productAmount: e.amount,
      productDate: e.dateCreated,
      productSale: e.sale,
      productCategory: e.category,
    });
    setOpenUpdate(true);
  };

  const handleDelete = (e) => {
    setOpenDelete(true);
    setId(e.key);
    setName(e.name);
  };

  const handleDeleteOk = () => {
    setOpenDelete(false);
    deleteProduct(id)
      .then((res) => {
        console.log(res);
        getData();
        ShowMessage("success","Delete successfully");
      })
      .catch((err) => {
        ErrorAuth(err);
      });
  };

  // Columns of table
  const columns = [
    {
      title: "Name",
      dataIndex: "name",
    },
    {
      title: "Cost",
      dataIndex: "cost",
    },
    {
      title: "Category",
      dataIndex: "category",
    },
    {
      title: "Amount",
      dataIndex: "amount",
    },
    {
      title: "Date created",
      dataIndex: "dateCreated",
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
              // take id when click edit
              handleDelete(key);
            }}
          >
            Delete
          </Button>
        </Space>
      ),
    },
  ];

  const dateFormatList = ["DD/MM/YYYY", "DD/MM/YY"];

  // Set Category
  const handleChange = (value) => {
    setCategoryName(value);
  };

  const dummyRequest = ({ file, onSuccess }) => {
    setTimeout(() => {
      onSuccess("ok");
    }, 0);
  };

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
        title="Create product"
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
            name="productName"
            rules={[
              { required: true, message: "Please input your product name!" },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="Description"
            name="productDesc"
            rules={[
              {
                required: true,
                message: "Please input your product description!",
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="Cost"
            name="productCost"
            rules={[
              {
                required: true,
                message: "Please input your product cost!",
              },
            ]}
          >
            <InputNumber
              prefix="$"
              parser={(value) => value.replace(/\$\s?|(,*)/g, "")}
            />
          </Form.Item>

          <Form.Item
            label="Amount"
            name="productAmount"
            rules={[
              {
                required: true,
                message: "Please input your product amount!",
              },
            ]}
          >
            <InputNumber parser={(value) => value.replace(/\$\s?|(,*)/g, "")} />
          </Form.Item>

          <Form.Item
            label="Category"
            name="productCategory"
            rules={[
              {
                required: true,
                message: "Please choose your category for product!",
              },
            ]}
          >
            <Select
              style={{ width: 120 }}
              // defaultValue={categories[0]}
              onChange={handleChange}
              options={category}
            />
          </Form.Item>

          <Form.Item label="Sale" name="productSale">
            <InputNumber prefix="%" min={0} max={100} />
          </Form.Item>

          <Form.Item label="RangePicker" name="dateSale">
            <RangePicker format={dateFormatList} />
          </Form.Item>

          {/* Button create */}
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
        title="Update product"
        open={openUpdate}
        // onOk={handleOk}
        okText="Create"
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
            name="productName"
            rules={[
              { required: true, message: "Please input your product name!" },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="Description"
            name="productDesc"
            rules={[
              {
                required: true,
                message: "Please input your product description!",
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="Cost"
            name="productCost"
            rules={[
              {
                required: true,
                message: "Please input your product cost!",
              },
            ]}
          >
            <InputNumber
              prefix="$"
              parser={(value) => value.replace(/\$\s?|(,*)/g, "")}
            />
          </Form.Item>

          <Form.Item
            label="Amount"
            name="productAmount"
            rules={[
              {
                required: true,
                message: "Please input your product amount!",
              },
            ]}
          >
            <InputNumber parser={(value) => value.replace(/\$\s?|(,*)/g, "")} />
          </Form.Item>

          <Form.Item
            label="Category"
            name="productCategory"
            rules={[
              {
                required: true,
                message: "Please choose your category for product!",
              },
            ]}
          >
            <Select
              style={{ width: 120 }}
              // defaultValue={categories[0]}
              onChange={handleChange}
              options={category}
            />
          </Form.Item>

          <Form.Item label="Sale" name="productSale">
            <InputNumber prefix="%" min={0} max={100} />
          </Form.Item>

          <Form.Item label="RangePicker" name="dateSale">
            <RangePicker format={dateFormatList} />
          </Form.Item>

          <Form.Item label="Images" name="productImages">
            <Upload
              customRequest={dummyRequest}
              multiple
              listType="picture-card"
              accept=".png,.jpeg,.jpg"
              // beforeUpload={(file) => {
              //   console.log(file);
              // }}
            >
              <div>
                <PlusOutlined />
                <div style={{ marginTop: 8 }}>Upload</div>
              </div>
            </Upload>
          </Form.Item>

          {/* Button create */}
          <Form.Item
            wrapperCol={{
              offset: 6,
              span: 16,
            }}
          >
            <Button type="primary" htmlType="submit">
              Update
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
        <p>
          Are you sure to delete product <strong>{name}</strong> ?
        </p>
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
};
export default App;

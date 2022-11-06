import {
  Button,
  Input,
  message,
  Space,
  Switch,
  Table,
  Form,
  Select,
} from "antd";
import { Content } from "antd/lib/layout/layout";
import Modal from "antd/lib/modal/Modal";
import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import ErrorAuth from "../../../utils/ErrorAuth";

const statesList = [
  "An Giang",
  "Bac Giang",
  "Bac Kan",
  "Bac Lieu",
  "Bac Ninh",
  "Ba Ria-Vung Tau",
  "Ben Tre",
  "Binh Dinh",
  "Binh Duong",
  "Binh Phuoc",
  "Binh Thuan",
  "Ca Mau",
  "Cao Bang",
  "Dac Lak",
  "Dac Nong",
  "Dien Bien",
  "Dong Nai",
  "Dong Thap",
  "Gia Lai",
  "Ha Giang",
  "Hai Duong",
  "Ha Nam",
  "Ha Tay",
  "Ha Tinh",
  "Hau Giang",
  "Hoa Binh",
  "Hung Yen",
  "Khanh Hoa",
  "Kien Giang",
  "Kon Tum",
  "Lai Chau",
  "Lam Dong",
  "Lang Son",
  "Lao Cai",
  "Long An",
  "Nam Dinh",
  "Nghe An",
  "Ninh Binh",
  "Ninh Thuan",
  "Phu Tho",
  "Phu Yen",
  "Quang Binh",
  "Quang Nam",
  "Quang Ngai",
  "Quang Ninh",
  "Quang Tri",
  "Soc Trang",
  "Son La",
  "Tay Ninh",
  "Thai Binh",
  "Thai Nguyen",
  "Thanh Hoa",
  "Thua Thien-Hue",
  "Tien Giang",
  "Tra Vinh",
  "Tuyen Quang",
  "Vinh Long",
  "Vinh Phuc",
  "Yen Bai",
  "Can Tho",
  "Da Nang",
  "Hai Phong",
  "Hanoi",
  "Ho Chi Minh",
];

function App() {
  const [data, setData] = useState([]);

  let navigate = useNavigate();
  // Id of username to edit
  const [username, setUsername] = useState("");
  const [id, setId] = useState("0");
  const [changeSwitch, setSwitch] = useState(false);

  // Form
  const [form] = Form.useForm();

  useEffect(() => {
    getData();
  }, []);

  const getData = async () => {
    await axios
      .get("http://localhost:8080/api/user/all")
      .then((res) => {
        setData(
          res.data.map((item) => {
            setSwitch(item.details.userRole === "ADMIN" ? true : false);
            return {
              key: item.userId,
              name: item.userName,
              email: item.userEmail,
              states: item.details.userCountry,
              address: item.details.userAddress,
              role: item.details.userRole,
              phone: item.details.userPhone,
            };
          })
        );
      })
      .catch((err) => {
        ErrorAuth(err);
        
      });
  };

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
  const [openUpdate, setOpenUpdate] = useState(false);
  const [openDelete, setOpenDelete] = useState(false);

  const [states, setState] = useState("");

  const success = (mes) => {
    message.success(`${mes}`, 2);
  };

  const error = (mes) => {
    message.error(`${mes}`, 2);
  };

  // Handle
  const handleUpdateRole = (email, isCheck) => {
    console.log(email);
    console.log(isCheck);
    let role = isCheck ? "ADMIN" : "GUEST";

    axios
      .put(
        `http://localhost:8080/api/user/changeRole?email=${email}&role=${role}`
      )
      .then((res) => {
        console.log(res);
        success("Update role successfully!");
      })
      .catch((err) => {
        console.log(err);
        error("Update role failed! ");
      });
  };

  const onFinishUpdate = (values) => {
    const json = {
      userId: id,
      userName: values.userName,
      userEmail: values.userEmail,
      details: {
        userPhone: values.userPhone,
        userAddress: values.userAddress,
        userCountry: values.userCountry,
        userRole: values.userRole,
      },
    };
    console.log(json);
    console.log(values);
    axios
      .put("http://localhost:8080/api/user/update", json)
      .then((res) => {
        success("Update success");
        // console.log(res);
        getData();
      })
      .catch((err) => {
        console.log(err);
        error(err.response.data.message);
      });

    setOpenUpdate(false);
  };

  const handleUpdate = (e) => {
    setUsername(e.name);
    setId(e.key);
    form.setFieldsValue({
      userName: e.name,
      userEmail: e.email,
      userPhone: e.phone,
      userCountry: e.states,
      userAddress: e.address,
      userRole: e.role,
    });
    setOpenUpdate(true);
  };

  const handleDelete = (e) => {
    setUsername(e.name);
    setId(e.key);
    setOpenDelete(true);
  };

  const handleDeleteOk = () => {
    setOpenDelete(false);
    console.log("Run delete");
    axios
      .delete("http://localhost:8080/api/user/delete", {
        params: {
          id: id,
        },
      })
      .then((res) => {
        // console.log(res);
        success("Delete success");
        getData();
      })
      .catch((err) => {
        // console.log(err);
        error(err.response.data.message);
      });
  };

  const handleChangeState = (e) => {
    setState(e);
  };

  // ========> COLUMN HERE <======== //
  const columns = [
    {
      title: "User Name",
      dataIndex: "name",
    },
    {
      title: "Email",
      dataIndex: "email",
    },
    {
      title: "Role",
      render: (value) => (
        <Switch
          checkedChildren="ADMIN"
          unCheckedChildren="GUEST"
          defaultChecked={value.role === "ADMIN" ? true : false}
          onChange={(checked) => {
            handleUpdateRole(value.email, checked);
          }}
        />
      ),
      sorter: (a, b) => a.role.localeCompare(b.role),
    },
    {
      title: "States",
      dataIndex: "states",
    },
    {
      title: "Address",
      dataIndex: "address",
    },
    {
      title: "Action",
      render: (value) => (
        <Space size="middle">
          <Button
            type="primary"
            onClick={() => {
              handleUpdate(value);
            }}
          >
            Edit
          </Button>
          <Button
            type="primary"
            danger
            onClick={() => {
              handleDelete(value);
            }}
          >
            Delete
          </Button>
        </Space>
      ),
    },
  ];

  /* eslint-disable no-template-curly-in-string */
  const validateMessages = {
    required: "Please input your ${label}!",
    types: {
      email: "${label} is not a valid email!",
    },
  };
  /* eslint-enable no-template-curly-in-string */

  const layout = {
    labelCol: { span: 5 },
    wrapperCol: { span: 18 },
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

      {/* Modal update */}
      <Modal
        title={`Update ${username}`}
        open={openUpdate}
        okText="Save"
        onCancel={() => {
          setOpenUpdate(false);
        }}
        footer={null}
      >
        <Form
          {...layout}
          form={form}
          name="user-info"
          onFinish={onFinishUpdate}
          validateMessages={validateMessages}
        >
          <Form.Item
            name={"userName"}
            label="Username"
            rules={[
              {
                required: true,
              },
            ]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name={"userEmail"}
            label="Email"
            rules={[
              {
                required: true,
                type: "email",
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            name={"userRole"}
            label="Role"
            rules={[
              {
                required: true,
              },
            ]}
          >
            <Select placeholder="Select a role for user" allowClear>
              <Select.Option value="ADMIN">ADMIN</Select.Option>
              <Select.Option value="GUEST">GUEST</Select.Option>
            </Select>
          </Form.Item>

          <Form.Item name={"userPhone"} label="Phone">
            <Input />
          </Form.Item>

          <Form.Item label="States" name={"userCountry"}>
            <Select onChange={handleChangeState}>
              {statesList.map((states) => (
                <Select.Option key={states} value={states}>
                  {states}
                </Select.Option>
              ))}
            </Select>
          </Form.Item>

          {/* Input Address */}
          <Form.Item name={"userAddress"} label="Address">
            <Input.TextArea />
          </Form.Item>

          {/* Button save */}
          <Form.Item
            wrapperCol={{
              offset: 5,
            }}
          >
            <Button type="primary" htmlType="submit">
              Save change
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
          Are you sure to delete <strong>{username}</strong>?
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
}
export default App;

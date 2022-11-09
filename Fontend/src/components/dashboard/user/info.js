import { Button, Form, Input, message, Select } from "antd";
import axios from "axios";
import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { getUserById, updateUser } from "../../../services/users";
import ErrorAuth from "../../../utils/errorAuth";
import ShowMessage from "../../../utils/message";

var id = localStorage.getItem("userId");

const layout = {
  labelCol: {
    span: 4,
  },
  wrapperCol: {
    span: 16,
  },
};

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

const validateMessages = {
  required: "${label} is required!",
  types: {
    email: "${label} is not a valid email!",
  },
};

function App() {
  const [states, setState] = useState("");

  const [form] = Form.useForm();

  useEffect(() => {
    getUserById(id).then((res) => {
      console.log(res.data);
        form.setFieldsValue({
          userName: res.data.userName,
          userEmail: res.data.userEmail,
          userPhone: res.data.userPhone,
          userAddress: res.data.userAddress,
          userCountry: res.data.userCountry,
        });
      })
      .catch((error) => {
        ErrorAuth(error);
      });
  }, []);

  const onFinish = (values) => {
    console.log(values);
    updateUser(id, values)
      .then((res) => {
        console.log(res);
        ShowMessage("success", "Update user successfully!");
      })
      .catch((error) => {
        ErrorAuth(error);
      });
  };

  const handleChange = (e) => {
    // console.log(e);
    setState(e);
  };

  return (
    <Form
      form={form}
      {...layout}
      name="user-info"
      onFinish={onFinish}
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
        name={"userPhone"}
        label="Phone"
        rules={[
          {
            required: true,
          },
        ]}
      >
        <Input />
      </Form.Item>

      <Form.Item label="States" name={"userCountry"}>
        <Select onChange={handleChange}>
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

      <Form.Item
        name={"userPassword"}
        label="Password"
        rules={[
          {
            required: true,
            message: "You must input your password to change!",
          },
        ]}
      >
        <Input.Password />
      </Form.Item>

      {/* Button save */}
      <Form.Item
        wrapperCol={{
          ...layout.wrapperCol,
          offset: 4,
        }}
      >
        <Button type="primary" htmlType="submit">
          Save change
        </Button>
      </Form.Item>
    </Form>
  );
}
export default App;

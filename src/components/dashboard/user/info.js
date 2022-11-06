import { Button, Form, Input, InputNumber, Select } from "antd";
import axios from "axios";
import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import authToken from "../../../utils/authToken";
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
  const [userPassword, setUserPassword] = useState("");
  const [userDetailsId, setUserDetailsId] = useState("");
  const [states, setState] = useState("");

  const [form] = Form.useForm();

  useEffect(() => {
    var userId = localStorage.getItem("userId");
    console.log(
      "From content: " + axios.defaults.headers.common["Authorization"]
    );
    axios
      .get("http://localhost:8080/api/user/id/", { params: { id: userId } })
      .then((res) => {
        setUserPassword(res.data.userPassword);
        setUserDetailsId(res.data.details.userDetailsID);
        form.setFieldsValue({
          userName: res.data.userName,
          userEmail: res.data.userEmail,
          userPhone: res.data.details.userPhone,
          userAddress: res.data.details.userAddress,
          userCountry: res.data.details.userCountry,
        });
      })
      .catch((error) => {
        console.log(axios.defaults.headers.common["Authorization"]);
        console.log(error.response);
      });
  }, []);

  const onFinish = (values) => {
    var userCountry = states;
    var userId = localStorage.getItem("userId");

    var data = {
      userId: userId,
      userName: values.userName,
      userEmail: values.userEmail,
      userPassword: userPassword,
      details: {
        userDetailsID: userDetailsId,
        userPhone: values.userPhone,
        userAddress: values.userAddress,
        userCountry: userCountry,
      },
    };

    axios
      .put("http://localhost:8080/api/user/update", data)
      .then((res) => {
        console.log(res);
      })
      .catch((error) => {
        console.log(error.response.data.message);
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

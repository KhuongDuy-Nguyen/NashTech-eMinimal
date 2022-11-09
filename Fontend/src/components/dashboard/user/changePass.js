import { Button, Form, Input } from "antd";
import React from "react";
import { changePassword } from "../../../services/users";
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

function App() {
  const [form] = Form.useForm();

  const onFinish = (values) => {
    console.log(values);
    if (values.newPassword !== values.confirmPassword) {
      ShowMessage("error", "New password not same");
    } else {
      changePassword(id, values)
        .then((res) => {
          ShowMessage("success", "Change password successfully!");
        })
        .catch((error) => {
            ErrorAuth(error);
            // ShowMessage("error", "Change password failed!");
        });
    }
  };

  return (
    <Form form={form} {...layout} name="user-info" onFinish={onFinish}>
      <Form.Item
        name={"oldPassword"}
        label="Old Password"
        rules={[
          {
            required: true,
            message: "You must input your password to change!",
          },
        ]}
      >
        <Input.Password />
      </Form.Item>

      <Form.Item
        name={"newPassword"}
        label="New Password"
        rules={[
          {
            required: true,
            message: "You must input new password!",
          },
        ]}
      >
        <Input.Password />
      </Form.Item>
      <Form.Item
        name={"confirmPassword"}
        label="Confirm Password"
        rules={[
          {
            required: true,
            message: "You must input confirm password!",
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

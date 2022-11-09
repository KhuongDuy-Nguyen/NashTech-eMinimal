import axios from "axios";

const getAllUser = async () => {
  return await axios.get("http://localhost:8080/api/user/all");
};

const getUserById = async (id) => {
  return await axios.get(`http://localhost:8080/api/user/id?id=${id}`);
};

const createUser = async (data) => {
  return await axios.post("http://localhost:8080/api/user/create", {
    username: data.username,
    password: data.password,
    email: data.email,
    role: data.role,
  });
};

const updateUser = async (id, values) => {
  return await axios.put("http://localhost:8080/api/user/update", {
    userId: id,
    userName: values.userName,
    userEmail: values.userEmail,
    userPhone: values.userPhone,
    userAddress: values.userAddress,
    userCountry: values.userCountry,
    userPassword: values.userPassword,
  });
};

const updateUserRole = async (email, role) => {
  return await axios.put(
    `http://localhost:8080/api/user/changeRole?email=${email}&role=${role}`
  );
};

const changePassword = async (id, value) => {
  return await axios.put(`http://localhost:8080/api/user/changePass`, {
    userId: id,
    oldPass: value.oldPassword,
    newPass: value.newPassword,
  });
};

const deleteUser = async (id) => {
  return await axios.delete(`http://localhost:8080/api/user/delete?id=${id}`);
};

// Auth
const loginUser = async (username, pass) => {
  return await axios.post("http://localhost:8080/api/auth/login", {
    userName: username,
    userPassword: pass,
  });
};

const registerUser = async (username, email, pass) => {
  return await axios.post("http://localhost:8080/api/auth/register", {
    userName: username,
    userEmail: email,
    userPassword: pass,
  });
};

export {
  getAllUser,
  getUserById,
  createUser,
  updateUser,
  updateUserRole,
  deleteUser,
  loginUser,
  registerUser,
  changePassword,
};

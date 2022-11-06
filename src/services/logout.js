export default function logout() {
  localStorage.removeItem("token");
  window.location.href = "/login";
}
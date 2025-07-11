import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function Login() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ mobileNumber: "", password: "" });
  const [loading, setLoading] = useState(false);

  const baseURL = process.env.REACT_APP_BACK_END_BASE_URL || "http://localhost:8080";

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!form.mobileNumber.trim() || !form.password.trim()) {
      toast.error("Please enter both mobile number and password.");
      return;
    }

    setLoading(true);
    try {
      const response = await fetch(`${baseURL}/api/auth/v1/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(form),
      });

      const data = await response.json();

      if (response.ok && data.token) {
        localStorage.setItem("token", data.token);
        toast.success("Login successful! Redirecting...");
        setTimeout(() => navigate("/prescriptions"), 1500);
      } else {
        toast.error(data.message || "Invalid mobile number or password.");
      }
    } catch (err) {
      toast.error("Login failed. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <ToastContainer position="top-right" autoClose={3000} hideProgressBar closeOnClick pauseOnHover />
      <div style={styles.container}>
        <div style={styles.card} role="main" aria-label="Login form">
          <h2 style={styles.title}>Doctor Login</h2>

          <form onSubmit={handleSubmit}>
            <label htmlFor="mobileNumber" style={styles.label}>
              Mobile Number
            </label>
            <input
              id="mobileNumber"
              name="mobileNumber"
              type="text"
              placeholder="Enter your mobile number"
              value={form.mobileNumber}
              onChange={handleChange}
              style={styles.input}
              autoComplete="tel"
              disabled={loading}
              aria-required="true"
            />

            <label htmlFor="password" style={styles.label}>
              Password
            </label>
            <input
              id="password"
              name="password"
              type="password"
              placeholder="Enter your password"
              value={form.password}
              onChange={handleChange}
              style={styles.input}
              autoComplete="current-password"
              disabled={loading}
              aria-required="true"
            />

            <button
              style={{ ...styles.button, ...(loading ? styles.buttonDisabled : {}) }}
              disabled={loading}
              aria-busy={loading}
              type="submit"
            >
              {loading ? "Logging in..." : "Login as Doctor"}
            </button>
          </form>

          <p style={styles.registerText}>
            Don't have an account?{" "}
            <Link to="/register" style={styles.registerLink}>
              Register here
            </Link>
          </p>
        </div>
      </div>
    </>
  );
}

const styles = {
  container: {
    height: "100vh",
    backgroundColor: "#fff",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
    padding: "20px",
  },
  card: {
    backgroundColor: "#f9f9f9",
    padding: "40px 50px",
    borderRadius: "12px",
    boxShadow: "0 8px 20px rgba(0,0,0,0.1)",
    width: "360px",
    maxWidth: "100%",
    textAlign: "left",
  },
  title: {
    marginBottom: "24px",
    color: "#222",
    textAlign: "center",
    fontWeight: "700",
  },
  label: {
    display: "block",
    marginBottom: "6px",
    fontWeight: "600",
    color: "#333",
  },
  input: {
    width: "100%",
    padding: "12px 15px",
    marginBottom: "20px",
    borderRadius: "8px",
    border: "1px solid #ccc",
    fontSize: "16px",
    outline: "none",
    transition: "border-color 0.3s",
    boxSizing: "border-box",
  },
  button: {
    width: "100%",
    padding: "12px 15px",
    backgroundColor: "#007bff",
    color: "white",
    fontWeight: "600",
    fontSize: "16px",
    borderRadius: "8px",
    border: "none",
    cursor: "pointer",
    transition: "background-color 0.3s",
  },
  buttonDisabled: {
    backgroundColor: "#a2a6f4",
    cursor: "not-allowed",
  },
  registerText: {
    marginTop: "16px",
    fontSize: "14px",
    textAlign: "center",
    color: "#555",
  },
  registerLink: {
    color: "#007bff",
    fontWeight: "600",
    textDecoration: "none",
  },
};

export default Login;

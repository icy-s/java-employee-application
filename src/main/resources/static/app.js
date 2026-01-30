const API_BASE = "/api/employees";
const employeeList = document.getElementById("employee-list");
const employeeCount = document.getElementById("employee-count");
const statusBox = document.getElementById("status");
const createForm = document.getElementById("create-form");
const updateForm = document.getElementById("update-form");
const refreshButton = document.getElementById("refresh");
const clearButton = document.getElementById("clear");

const formatCurrency = (value, currency) =>
  new Intl.NumberFormat("en-US", {
    style: "currency",
    currency,
    maximumFractionDigits: 0,
  }).format(value);

const showStatus = (message, variant = "info") => {
  statusBox.textContent = message;
  statusBox.className = `status show ${variant}`.trim();
};

const hideStatus = () => {
  statusBox.className = "status";
};

const mapEmployee = (employee) => {
  const card = document.createElement("div");
  card.className = "employee-card";
  card.innerHTML = `
    <span class="badge">ID ${employee.id}</span>
    <h3>${employee.name}</h3>
    <div class="employee-meta">
      <span>📧 ${employee.email}</span>
      <span>🎂 ${employee.birth}</span>
      <span>💶 ${formatCurrency(employee.salary, "EUR")}</span>
      <span>💵 ${formatCurrency(employee.salary, "USD")}</span>
    </div>
    <div class="employee-actions">
      <button class="edit">Fill form</button>
      <button class="delete">Delete</button>
    </div>
  `;

  card.querySelector(".edit").addEventListener("click", () => {
    updateForm.id.value = employee.id;
    updateForm.email.value = employee.email;
    updateForm.salary.value = employee.salary;
    updateForm.scrollIntoView({ behavior: "smooth", block: "center" });
  });

  card.querySelector(".delete").addEventListener("click", async () => {
    const confirmed = window.confirm(
      `Delete employee ${employee.name}?`
    );
    if (!confirmed) {
      return;
    }
    await removeEmployee(employee.id);
  });

  return card;
};

const renderEmployees = (employees) => {
  employeeList.innerHTML = "";
  employees.forEach((employee) => employeeList.appendChild(mapEmployee(employee)));
  employeeCount.textContent = employees.length;
};

const fetchEmployees = async () => {
  try {
    const response = await fetch(API_BASE);
    if (!response.ok) {
      throw new Error("Unable to load the employee list.");
    }
    const employees = await response.json();
    renderEmployees(employees);
    hideStatus();
  } catch (error) {
    showStatus(error.message, "error");
  }
};

const createEmployee = async (payload) => {
  try {
    const response = await fetch(API_BASE, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    });

    if (!response.ok) {
      const text = await response.text();
      throw new Error(text || "Failed to create employee.");
    }

    showStatus("Employee created.", "success");
    await fetchEmployees();
  } catch (error) {
    showStatus(error.message, "error");
  }
};

const updateEmployee = async (id, email, salary) => {
  const params = new URLSearchParams();
  if (email) {
    params.append("email", email);
  }
  if (salary) {
    params.append("salary", salary);
  }

  try {
    const response = await fetch(`${API_BASE}/${id}?${params.toString()}`, {
      method: "PUT",
    });
    if (!response.ok) {
      const text = await response.text();
      throw new Error(text || "Failed to update employee.");
    }
    showStatus("Employee updated.", "success");
    await fetchEmployees();
  } catch (error) {
    showStatus(error.message, "error");
  }
};

const removeEmployee = async (id) => {
  try {
    const response = await fetch(`${API_BASE}/${id}`, {
      method: "DELETE",
    });
    if (!response.ok) {
      const text = await response.text();
      throw new Error(text || "Failed to delete employee.");
    }
    showStatus("Employee deleted.", "success");
    await fetchEmployees();
  } catch (error) {
    showStatus(error.message, "error");
  }
};

createForm.addEventListener("submit", async (event) => {
  event.preventDefault();
  const payload = {
    name: createForm.name.value.trim(),
    email: createForm.email.value.trim(),
    birth: createForm.birth.value,
    salary: Number(createForm.salary.value),
  };
  await createEmployee(payload);
  createForm.reset();
});

updateForm.addEventListener("submit", async (event) => {
  event.preventDefault();
  const id = updateForm.id.value;
  if (!id) {
    showStatus("Provide an employee ID.", "error");
    return;
  }
  const email = updateForm.email.value.trim();
  const salary = updateForm.salary.value.trim();
  if (!email && !salary) {
    showStatus("Enter email or salary to update.", "error");
    return;
  }
  await updateEmployee(id, email || null, salary || null);
});

refreshButton.addEventListener("click", fetchEmployees);
clearButton.addEventListener("click", () => {
  createForm.reset();
  updateForm.reset();
  hideStatus();
});

fetchEmployees();
<h2>🔧 Testing Instructions for Appointment System Project</h2>

<p>To test the core functionalities of the Appointment Booking System during development, please follow the steps below:</p>

<ol>
  <li><strong>Create Doctor</strong><br>
      Use the <strong>Doctor Controller</strong> to create doctor records. This endpoint is kept <strong>public temporarily</strong> for testing purposes.
  </li>

  <li><strong>Create Lab Test</strong><br>
      Use the <strong>LabTest Controller</strong> to create lab test entries. This endpoint is also <strong>public during testing</strong>.
  </li>

  <li><strong>Create Diagnostic Center</strong><br>
      Use the <strong>Diagnostic Controller</strong> to add diagnostic center data. Like the others, this is <strong>publicly accessible for testing</strong>.
  </li>

  <li><strong>Create User</strong><br>
      Register a new user either via the <strong>UserAuthController</strong> or through the <strong>frontend registration page</strong>.
  </li>

  <li><strong>Login User</strong><br>
      Authenticate the user using the <strong>UserAuthController</strong> or via the <strong>frontend login page</strong>.
  </li>
</ol>

<blockquote>
  <strong>Note:</strong> The endpoints for Doctor, LabTest, and Diagnostic controllers are public <strong>only for development and testing</strong>. These will be restricted and moved under <strong>admin access</strong> once the admin dashboard is implemented.
</blockquote>

<p>After completing the above steps, all other operations (booking appointments, viewing doctors, etc.) can be performed from the <strong>frontend interface</strong>.</p>

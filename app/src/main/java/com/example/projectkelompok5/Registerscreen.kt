package com.example.projectkelompok5

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    // 3 input tambahan
    var phone by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }

    var savedMessage by remember { mutableStateOf("") }

    val pink = Color(0xFFE91E8C)
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrasi Akun", fontWeight = FontWeight.Bold, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = pink)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF0F5))
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Data Pribadi", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = pink)

            RegisterField("First Name", firstName, { firstName = it }, Icons.Default.Person)
            RegisterField("Last Name", lastName, { lastName = it }, Icons.Default.Person)
            RegisterField("Username", username, { username = it }, Icons.Default.AccountCircle)
            RegisterField("Email", email, { email = it }, Icons.Default.Email,
                keyboardType = KeyboardType.Email)

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = pink) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null,
                            tint = pink
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = pink, focusedLabelColor = pink
                )
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text("Data Tambahan", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = pink)

            RegisterField("Nomor Telepon", phone, { phone = it }, Icons.Default.Phone,
                keyboardType = KeyboardType.Phone)
            RegisterField("Tanggal Lahir (DD/MM/YYYY)", birthDate, { birthDate = it }, Icons.Default.DateRange)
            RegisterField("Jenis Kelamin", gender, { gender = it }, Icons.Default.Face)
            RegisterField("Alamat", address, { address = it }, Icons.Default.Home)
            RegisterField("Bio Singkat", bio, { bio = it }, Icons.Default.Info)

            Spacer(modifier = Modifier.height(8.dp))

            // Pesan berhasil
            if (savedMessage.isNotEmpty()) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = savedMessage,
                        color = Color(0xFF2E7D32),
                        modifier = Modifier.padding(12.dp),
                        fontSize = 14.sp
                    )
                }
            }

            // Tombol Save
            Button(
                onClick = {
                    // Simpan ke UserData (hardcode / in-memory)
                    UserData.firstName = firstName.ifEmpty { UserData.firstName }
                    UserData.lastName = lastName.ifEmpty { UserData.lastName }
                    UserData.username = username.ifEmpty { UserData.username }
                    UserData.email = email.ifEmpty { UserData.email }
                    UserData.password = password.ifEmpty { UserData.password }
                    UserData.phone = phone.ifEmpty { UserData.phone }
                    UserData.birthDate = birthDate.ifEmpty { UserData.birthDate }
                    UserData.gender = gender.ifEmpty { UserData.gender }
                    UserData.address = address.ifEmpty { UserData.address }
                    UserData.bio = bio.ifEmpty { UserData.bio }
                    savedMessage = "✅ Data berhasil disimpan! Silakan login."
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = pink)
            ) {
                Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
                Text("Save", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun RegisterField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val pink = Color(0xFFE91E8C)
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null, tint = pink) },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = pink,
            focusedLabelColor = pink
        )
    )
}
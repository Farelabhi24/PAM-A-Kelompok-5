package com.example.projectkelompok5

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

private val PrimaryPink = Color(0xFFE91E8C)
private val LightPink = Color(0xFFFFF0F5)
private val TextGray = Color(0xFF888888)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profil Saya", fontWeight = FontWeight.Bold, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryPink),
                actions = {
                    IconButton(onClick = { navController.navigate(Routes.AVATAR) }) {
                        Icon(Icons.Default.Face, contentDescription = "Avatar", tint = Color.White)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightPink)
                .padding(innerPadding)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header kartu profil
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryPink)
                    .padding(vertical = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .border(3.dp, Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = UserData.firstName.take(1).uppercase(),
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryPink
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${UserData.firstName} ${UserData.lastName}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Text("@${UserData.username}", color = Color(0xFFFFCDD2), fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { navController.navigate(Routes.AVATAR) },
                modifier = Modifier.padding(bottom = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryPink)
            ) {
                Icon(Icons.Default.Face, contentDescription = null, modifier = Modifier.padding(end = 6.dp))
                Text("Lihat Avatar Saya")
            }

            // Informasi Akun
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Informasi Akun", fontWeight = FontWeight.Bold, color = PrimaryPink, fontSize = 15.sp)
                    ProfileInfoRow(Icons.Default.Email, "Email", UserData.email)
                    ProfileInfoRow(Icons.Default.AccountCircle, "Username", UserData.username)
                    ProfileInfoRow(Icons.Default.Lock, "Password", "••••••••") // Menutupi password adalah ide bagus!
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Data Pribadi
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Data Pribadi", fontWeight = FontWeight.Bold, color = PrimaryPink, fontSize = 15.sp)
                    ProfileInfoRow(Icons.Default.Phone, "Telepon", UserData.phone)
                    ProfileInfoRow(Icons.Default.DateRange, "Tanggal Lahir", UserData.birthDate)
                    ProfileInfoRow(Icons.Default.Face, "Jenis Kelamin", UserData.gender)
                    ProfileInfoRow(Icons.Default.Home, "Alamat", UserData.address)
                    ProfileInfoRow(Icons.Default.Info, "Bio", UserData.bio)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tombol Logout dengan perbaikan Clear Backstack
            Button(
                onClick = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true } // Menghapus semua riwayat halaman agar aman
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
            ) {
                Icon(Icons.Default.ExitToApp, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
                Text("Logout", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ProfileInfoRow(icon: ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = PrimaryPink, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(label, fontSize = 11.sp, color = TextGray)
            Text(value.ifEmpty { "-" }, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
    }
}
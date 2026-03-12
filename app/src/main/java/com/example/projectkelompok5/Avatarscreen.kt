package com.example.projectkelompok5

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvatarScreen(navController: NavController) {
    val pink = Color(0xFFE91E8C)

    // State checkbox untuk tiap komponen wajah
    var showBrow by remember { mutableStateOf(true) }
    var showEye by remember { mutableStateOf(true) }
    var showNose by remember { mutableStateOf(true) }
    var showMouth by remember { mutableStateOf(true) }

    // URL gambar komponen wajah dari internet (Picsum sebagai placeholder)
    // Ganti URL ini dengan gambar komponen wajah yang sebenarnya dari resource asli
    val faceBase   = "https://picsum.photos/seed/face-base/300/300"
    val browUrl    = "https://picsum.photos/seed/face-brow/300/300"
    val eyeUrl     = "https://picsum.photos/seed/face-eye/300/300"
    val noseUrl    = "https://picsum.photos/seed/face-nose/300/300"
    val mouthUrl   = "https://picsum.photos/seed/face-mouth/300/300"

    /*
     * ─── CARA PAKAI GAMBAR DARI RESOURCE LOKAL ───────────────────────────────
     * Jika resource gambar sudah tersedia di res/drawable, ganti AsyncImage
     * dengan Image + painterResource seperti contoh berikut:
     *
     * import androidx.compose.foundation.Image
     * import androidx.compose.ui.res.painterResource
     *
     * Image(
     *     painter = painterResource(id = R.drawable.avatar_base),
     *     contentDescription = "Wajah",
     *     modifier = Modifier.fillMaxSize()
     * )
     * ─────────────────────────────────────────────────────────────────────────
     */

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Avatar Saya", fontWeight = FontWeight.Bold, color = Color.White) },
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
                .background(Color(0xFFFCE4EC))
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // ── Area Wajah Avatar (Layer) ────────────────────────────────────
            Card(
                modifier = Modifier
                    .size(280.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1))
            ) {
                Box(modifier = Modifier.fillMaxSize()) {

                    // Layer 1: Wajah dasar (selalu tampil)
                    AsyncImage(
                        model = faceBase,
                        contentDescription = "Wajah Dasar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Layer 2: Alis (Brow)
                    if (showBrow) {
                        AsyncImage(
                            model = browUrl,
                            contentDescription = "Alis",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .align(Alignment.TopCenter)
                                .padding(top = 40.dp)
                        )
                    }

                    // Layer 3: Mata (Eye)
                    if (showEye) {
                        AsyncImage(
                            model = eyeUrl,
                            contentDescription = "Mata",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .align(Alignment.TopCenter)
                                .padding(top = 90.dp)
                        )
                    }

                    // Layer 4: Hidung (Nose)
                    if (showNose) {
                        AsyncImage(
                            model = noseUrl,
                            contentDescription = "Hidung",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                                .align(Alignment.Center)
                        )
                    }

                    // Layer 5: Mulut (Mouth)
                    if (showMouth) {
                        AsyncImage(
                            model = mouthUrl,
                            contentDescription = "Mulut",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 40.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ── Checkbox Kontrol Komponen Wajah ─────────────────────────────
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AvatarCheckbox(label = "Brow", checked = showBrow, onCheckedChange = { showBrow = it }, color = pink)
                    AvatarCheckbox(label = "Eye", checked = showEye, onCheckedChange = { showEye = it }, color = pink)
                    AvatarCheckbox(label = "Nose", checked = showNose, onCheckedChange = { showNose = it }, color = pink)
                    AvatarCheckbox(label = "Mouth", checked = showMouth, onCheckedChange = { showMouth = it }, color = pink)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Centang/hapus centang untuk mengaktifkan\natau menonaktifkan komponen wajah",
                fontSize = 12.sp,
                color = Color(0xFF888888),
                modifier = Modifier.padding(horizontal = 24.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
fun AvatarCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    color: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = color,
                uncheckedColor = Color(0xFFBBBBBB)
            )
        )
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

package com.test.test.ui.home

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.test.test.data.model.Data
import com.test.test.databinding.EmployeeItemBinding
import androidx.compose.material3.MaterialTheme as MaterialTheme1

@Composable
fun slides(
    slides: List<Data>,
    itemClickList: (Data) -> Unit
)
{
    val state = rememberLazyListState()
    if (!state.canScrollForward)
    {
        Log.i("SCROLL", "loadMore")
    }

    LazyColumn(
        state = state
    ) {
        items(slides.toMutableList()) { slide ->
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp, Dp(0f)), contentAlignment = Alignment.Center
            ) {
                if (slides.isEmpty())
                {
                    CircularProgressIndicator(
                        modifier = Modifier.width(24.dp),
                        color = MaterialTheme1.colorScheme.secondary,
                        trackColor = MaterialTheme1.colorScheme.surfaceVariant,
                    )
                } else
                {
                    AndroidView(
                        factory = {ctx->
                            val binding = EmployeeItemBinding.inflate(LayoutInflater.from(ctx))
                            binding.name.text = slide.employee_name
                            binding.age.text = slide.employee_age.toString()
                            binding.salary.text = slide.employee_salary.toString()
                            binding.id.text = slide.id.toString()
                            binding.age.setTextColor(
                            if(slide.employee_age in 25..35)
                            {
                                    Color.parseColor("#00ff00")
                            }
                            else{
                                 Color.parseColor("#ff0000")
                            }
                            )
                            binding.salary.setTextColor(
                                if(slide.employee_salary > 1000)
                                {
                                    Color.parseColor("#00ff00")
                                }
                                else{
                                    Color.parseColor("#ff0000")
                                }
                            )
                            binding.card.setOnClickListener {
                                itemClickList(slide)
                            }
                            binding.root
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp, Dp(0f))
                    )
                }
            }
        }
    }
}

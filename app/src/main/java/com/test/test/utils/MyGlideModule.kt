package com.test.test.utils

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.test.test.domain.repository.client
import java.io.InputStream

@GlideModule
class MyGlideModule : AppGlideModule()
{
    override fun registerComponents(context: Context, glide: Glide, registry: Registry)
    {
        val factory = OkHttpUrlLoader.Factory(client)
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java, factory
        )
    }
}
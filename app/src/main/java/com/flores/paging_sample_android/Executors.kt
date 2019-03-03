package com.flores.paging_sample_android

import java.util.concurrent.Executors

/**
 * Utility method to run blocks on a dedicated background thread, may be used for io/database work.
 */
fun ioThread(f: () -> Unit) {
    Executors.newSingleThreadExecutor().execute(f)
}
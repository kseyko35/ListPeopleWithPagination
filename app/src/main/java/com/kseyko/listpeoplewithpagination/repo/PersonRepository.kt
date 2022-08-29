package com.kseyko.listpeoplewithpagination.repo

import com.kseyko.listpeoplewithpagination.source.DataSource
import com.kseyko.listpeoplewithpagination.source.FetchCompletionHandler
import javax.inject.Inject

/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      28,August,2022        ║
╚════════════════════════════╝
 */

class PersonRepository @Inject constructor(private val source: DataSource) {

    fun fetchPersonList(next: String?, completionHandler: FetchCompletionHandler) {
        source.fetch(next, completionHandler)
    }

}
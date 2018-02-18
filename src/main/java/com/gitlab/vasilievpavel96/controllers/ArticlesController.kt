package com.gitlab.vasilievpavel96.controllers

import com.gitlab.vasilievpavel96.extensions.get
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.stream.createHTML
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "Article", urlPatterns = arrayOf("/articles/*"))
class ArticlesController : HttpServlet() {
    private lateinit var resources: ResourceBundle

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        resources = ResourceBundle.getBundle("Blog", req.locale)
        val path = req.pathInfo
        val document = createHTML(true).body {
            h1 {
                if (path != null) {
                    +resources["article_details"]
                } else {
                    +resources["articles"]
                }
            }
        }
        with(res.writer) {
            print(document)
            close()
        }
    }
}
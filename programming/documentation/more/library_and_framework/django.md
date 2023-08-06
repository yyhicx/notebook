# Django

## Django文档

开始：

*   初识Django
*   快速安装指南：
    *   Install: pip3 install django
*   编写你的第一个Django应用，第1部分：
    *   Create A Project: django-admin startproject project_name
    *   Create A App: python3 manage.py manage.py startapp app_name
    *   Run: python3 manage.py runserver 0.0.0.0:8000

## 常用库

Django Cors Headers:

*   Install:
    *   pip3 install django-cors-headers
    *   add `configuration` to project_name/project_name/settings.py

        ```python
        # configuration
        INSTALLED_APPS = [
            ...,
            'corsheaders',
            ...,
        ]
        MIDDLEWARE = [
            ...,
            'corsheaders.middleware.CorsMiddleware',
            'django.middleware.common.CommonMiddleware',
            ...,
        ]
        CORS_ALLOW_CREDENTIALS = True
        CORS_ORIGIN_ALLOW_ALL = True
        ```

Django REST Framework:

*   Install:
    *   pip3 install djangorestframework
    *   add `configuration` to project_name/project_name/settings.py

        ```python
        # configuration
        INSTALLED_APPS = [
            ...,
            'rest_framework',
        ]
        ```

PyMySQL:

*   Install:
    *   pip3 install pymysql
    *   add `code` to project_name/project_name/__init__.py

        ```python
        # code
        import pymysql
        pymysql.install_as_MySQLdb()
        ```

Django Grappelli:

*   Install:
    *   pip3 install django-grappelli
    *   add `configuration` to project_name/project_name/settings.py

        ```python
        # configuration
        INSTALLED_APPS = (
            'grappelli',
            'django.contrib.admin',
        )
        TEMPLATES = [
            {
                ...
                'OPTIONS': {
                    'context_processors': [
                        ...
                        'django.template.context_processors.request',
                        ...
                    ],
                },
            },
        ]
        ```

    *   add `code` to project_name/project_name/urls.py

        ```python
        # code
        from django.conf.urls import include
        urlpatterns = [
            path('grappelli/', include('grappelli.urls')),  # grappelli URLS
            path('admin/', admin.site.urls),  # admin site
        ]
        ```

    *   python3 manage.py collectstatic

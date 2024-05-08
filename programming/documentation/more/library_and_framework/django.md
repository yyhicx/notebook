# Django

## Django文档

src: open django_example project and run it

开始：

*   安装：`pip3 install django`。
*   编写你的第一个Django应用，第1部分：
    *   创建项目（Project）：`django-admin startproject project_name`。
    *   创建应用（App）：`python3 manage.py manage.py startapp app_name`。
    *   运行：`python3 manage.py runserver 0.0.0.0:8000`。
*   编写你的第一个Django应用，第2部分：
    *   改变Model的步骤：
        1.  编辑`models.py`文件。
        2.  运行`python3 manage.py makemigrations`为模型的改变生成迁移文件。
        3.  运行`python3 manage.py migrate`为应用数据库迁移。
    *   数据库迁移被分解为生成和应用两个命令。
    *   在启动交互解释器时，使用`python3 manage.py shell`比`python3`命令更好，会通过`DJANGO_SETTINGS_MODULE`配置环境变量：

        ```python
        from polls.models import Choice, Question
        from django.utils import timezone

        current_year = timezone.now().year

        """Create --- 1"""
        q = Question.objects.create(question_text='What\'s new?', pub_date=timezone.now())
        """Create --- 2"""
        q = Question(question_text='What\'s new?', pub_date=timezone.now())
        q.save()
        
        """Read --- 1"""
        Question.objects.all() # 查询 Question 中所有实例
        """Read --- 2"""
        Question.objects.filter(id=1) # 根据 ID 值查询
        Question.objects.filter(question_text__startswith='What') # 根据字符串值查询，获取多个实例
        """Read --- 3"""
        Question.objects.get(pub_date__year=current_year) # 根据日期值查询，获取单个实例，如果没有匹配或有多个匹配则会引发异常
        Question.objects.get(pk=1) # 根据主键值查询，等价于 id=1

        """Update --- 1"""
        q = Question.objects.get(pk=1) # 查询赋值对象
        q.quesstion_text = 'What\'s up?'
        q.save()
        """Update --- 2"""
        Question.objects.filter(question_text__startswith='What\'s new?').update(question_text='What\'s up?')
        
        """Delete --- 1"""
        q = Question.objects.get(pk=1)
        q.delete() # 删除对象
        """Delete --- 2"""
        Question.objects.filter(question_text__startswith='What\'s new?').delete()

        # 查看对象的变量或调用对象的方法
        q.id
        q.question_text
        q.pub_date
        q.was_published_recently()
        ```

    *   Django管理页面：
        *   创建管理员账户：`python3 manage.py createsuperuser`。
        *   删除管理员账户；
            1.  通过`python3 manage.py shell`打开交互解释器。
            2.  通过以下代码删除：

                ```python
                from django.contrib.auth.models import User
                User.objects.get(username='foobar', is_superuser=True).delete() # foobar 是管理员名称
                ```

*   编写你的第一个Django应用，第5部分：
    *   为什么需要编写测试：
        1.  测试将节约你的时间。
        2.  测试不仅能发现错误，还能预防错误。
        3.  测试使你的代码更有吸引力。
        4.  测试有利于团队协作。
    *   编写测试：
        1.  在app内的`test.py`中编写测试代码。
        2.  运行`python3 manage.py test app_name`来执行测试。
*   `settings.py`文件中各种设置项：
    *   `INSTALLED_APPS`：包括了会在你项目中启用的所有Django项目。
    *   `DATABASES`：设置数据库。
    *   `LANGUAGE_CODE`：指定应用程序的默认语言。
    *   `TIME_ZONE`：设置时区。

专题指南：

*   模型和数据库：
    *   模型：
        *   模型准确且唯一的描述了数据。它包含您储存的数据的重要字段和行为。
        *   每个模型都是一个Python的类，这些类继承`django.db.models.Model`。
        *   一般来说，每一个模型都映射一张数据库表。模型类的每个属性都相当于一个数据库的字段。

        ```python
        from django.db import models


        class Person(models.Model):
          first_name = models.CharField(max_length=30)
          last_name = models.CharField(max_length=30)


        class Musician(models.Model):
          first_name = models.CharField(max_length=50)
          last_name = models.CharField(max_length=50)
          instrument = models.CharField(max_length=100)


        class Album(models.Model):
          artist = models.ForeignKey(Musician, on_delete=models.CASCADE)
          name = models.CharField(max_length=100)
          release_date = models.DateField()
          num_stars = models.IntegerField()
        ```

    *   常用字段类型：

        ```python
        from django.db import models


        """CharField and TextField"""
        class Article(models.Model):
          title = models.CharField(max_length=100)
          content = models.TextField()


        """IntegerField and FloatField"""
        class Product(models.Model):
          price = models.IntegerField()
          weight = models.FloatField()


        """BooleanField"""
        class Task(models.Model):
          is_completed = models.BooleanField(default=False)


        """DateField, TimeField and DateTimeField"""
        class Event(models.Model):
          event_date = models.DateField()
          event_time = models.TimeField()
          creation_datetime = models.DateTimeField(auto_now_add=True)


        """EmailField, URLField, ImageField and FileField"""
        class UserProfile(models.Model):
          email = models.EmailField()
          website = models.URLField()
          profile_picture = models.ImageField(upload_to='profile_pics/')
          resume = models.FileField(upload_to='resumes/')


        """ForeignKey"""
        class Author(models.Model):
          name = models.CharField(max_length=100)


        class Book(models.Model):
          title = models.CharField(max_length=100)
          author = models.ForeignKey(Author, on_delete=models.CASCADE)
        ```

    *   关联关系：
        *   多对一关联：

            ```python
            """每个Car都有一个Manufacturer，一个Manufacturer制造多个Car"""
            from django.db import models


            class Manufacturer(models.Model):
              # ...
              pass


            class Car(models.Model):
              manufacturer = models.ForeignKey(Manufacturer, on_delete=models.CASCADE)
              # ...
            ```

        *   多对多关联：

            ```python
            """Pizza含有多种Topping，一种Topping可能存在于多个Pizza中"""
            from django.db import models


            class Topping(models.Model):
              # ...
              pass


            class Pizza(models.Model):
              # ...
              toppings = models.ManyToManyField(Topping)
            ```

        *   一对一关联：

            ```python
            """一个Place只有一个Restaurant，一个Restaurant只能在一个Place"""
            from django.db import models


            class Place(models.Model):
              name = models.CharField(max_length=50)
              address = models.CharField(max_length=80)


            class Restaurant(models.Model):
              place = models.OneToOneField(
                  Place,
                  on_delete=models.CASCADE,
                  primary_key=True,
              )
              serves_hot_dogs = models.BooleanField(default=False)
              serves_pizza = models.BooleanField(default=False)
            ```

    *   Meta选项：

        ```python
        from django.db import models


        class MyModel(models.Model):
          name = models.CharField(max_length=100)
          age = models.IntegerField()

          class Meta:
            ordering = ['name', '-age']  # 按 name 升序，然后按 age 降序排序
            db_table = 'custom_table_name'
        ```

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

*   Source:
    *   src: open django_example project and run it, then visit localhost:8000/blog

PyMySQL:

*   Install:
    *   pip3 install pymysql
    *   add `configuration` to project_name/project_name/setting.py

        ```python
        # configuration
        DATABASES = {
            'default': {
                'ENGINE': 'django.db.backends.mysql',
                'NAME': 'database_name',
                'USER': 'database_user',
                'PASSWORD': 'password',
                'HOST': '127.0.0.1',
                'PORT': '3306',
            },
        }
        ```

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

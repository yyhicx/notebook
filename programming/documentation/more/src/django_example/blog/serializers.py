from django.contrib.auth import get_user_model
from rest_framework import serializers
from .models import Article

User = get_user_model()


class ArticleSerializer(serializers.ModelSerializer):
    class Meta:
        model = Article
        fields = "__all__"
        read_only_fields = ("id", "author", "create_date")

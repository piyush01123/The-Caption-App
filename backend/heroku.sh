# heroku create image-caption-app

heroku container:push web --app image-caption-app && heroku container:release web --app image-caption-app && heroku logs --app image-caption-app --tail

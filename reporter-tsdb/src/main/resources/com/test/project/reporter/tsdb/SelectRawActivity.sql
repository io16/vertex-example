select json_build_object('activities', json_agg(activities_json))
 from (select json_build_object('from_local', activities.from_local,
                                'to_local', activities.to_local,
                                'user_id', activities.user_id,
                                'project_id', activities.project_id,
                                'type_id', activities.type_id,
                                'category_id', activities.category_id,
                                'process_id', activities.type_id,
                                'document_url', activities.document_url,
                                'duration_ms', activities.duration_ms) activities_json
      from (select *
            from {{DOMAIN_ACTIVITY_ID}}
            where user_id = {{USER_ID}}
              and project_id = {{PROJECT_ID}}
              and from_local >= '{{FROM_DATE}}'
              and from_local < '{{TO_DATE}}'
              and to_local <= '{{TO_DATE}}'
            order by from_local) activities) activities_json;

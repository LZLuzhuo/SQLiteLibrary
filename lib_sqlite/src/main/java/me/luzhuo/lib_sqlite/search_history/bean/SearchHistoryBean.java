/* Copyright 2020 Luzhuo. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.luzhuo.lib_sqlite.search_history.bean;

/**
 * Description:
 *
 * @Author: Luzhuo
 * @Creation Date: 2020/7/4 0:41
 * @Copyright: Copyright 2020 Luzhuo. All rights reserved.
 **/
public class SearchHistoryBean {
    private int id;
    public int type;
    public String content;

    public SearchHistoryBean(String content){
        this(0, content);
    }

    public SearchHistoryBean(int type, String content){
        this.type = type;
        this.content = content;
    }

    public SearchHistoryBean setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (content == null) return false;

        SearchHistoryBean that = (SearchHistoryBean) o;
        return type == that.type && content.equals(that.content);
    }
}
